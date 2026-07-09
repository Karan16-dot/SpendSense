package com.spendsense.expense.specification;

import com.spendsense.expense.dto.request.ExpenseFilterRequest;
import com.spendsense.expense.entity.Expense;
import com.spendsense.user.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExpenseSpecification {

    private ExpenseSpecification() {
    }

    public static Specification<Expense> filterExpenses(
            ExpenseFilterRequest request,
            User currentUser) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Current User
            predicates.add(
                    cb.equal(root.get("user"), currentUser)
            );

            // Deleted = false
            predicates.add(
                    cb.isFalse(root.get("deleted"))
            );

            if (request.getKeyword() != null &&
                    !request.getKeyword().isBlank()) {

                predicates.add(

                        cb.like(

                                cb.lower(root.get("title")),

                                "%" + request.getKeyword().toLowerCase() + "%"
                        )
                );
            }

            if (request.getCategoryId() != null) {

                predicates.add(

                        cb.equal(

                                root.get("category").get("id"),

                                request.getCategoryId()

                        )
                );
            }

            if (request.getPaymentMethod() != null) {

                predicates.add(

                        cb.equal(

                                root.get("paymentMethod"),

                                request.getPaymentMethod()

                        )
                );
            }

            if (request.getMerchant() != null &&
                    !request.getMerchant().isBlank()) {

                predicates.add(

                        cb.like(

                                cb.lower(root.get("merchant")),

                                "%" + request.getMerchant().toLowerCase() + "%"
                        )
                );
            }

            if (request.getFromDate() != null) {

                predicates.add(

                        cb.greaterThanOrEqualTo(

                                root.get("transactionDate"),

                                request.getFromDate()

                        )
                );
            }

            if (request.getToDate() != null) {

                predicates.add(

                        cb.lessThanOrEqualTo(

                                root.get("transactionDate"),

                                request.getToDate()

                        )
                );
            }

            if (request.getMinAmount() != null) {

                predicates.add(

                        cb.greaterThanOrEqualTo(

                                root.get("amount"),

                                request.getMinAmount()

                        )
                );
            }

            if (request.getMaxAmount() != null) {

                predicates.add(

                        cb.lessThanOrEqualTo(

                                root.get("amount"),

                                request.getMaxAmount()

                        )
                );
            }

            return cb.and(
                    predicates.toArray(new Predicate[0])
            );

        };

    }

}