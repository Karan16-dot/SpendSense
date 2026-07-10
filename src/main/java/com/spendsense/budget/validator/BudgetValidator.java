package com.spendsense.budget.validator;

import com.spendsense.budget.dto.request.CreateBudgetRequest;
import com.spendsense.budget.repository.BudgetRepository;
import com.spendsense.category.entity.Category;
import com.spendsense.common.exception.InvalidOperationException;
import com.spendsense.common.exception.ResourceAlreadyExistsException;
import com.spendsense.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BudgetValidator {

    private final BudgetRepository budgetRepository;

    public void validate(
            CreateBudgetRequest request,
            User user,
            Category category) {

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {

            throw new InvalidOperationException(
                    "Budget amount must be greater than zero."
            );
        }

        if (request.getStartDate().isAfter(request.getEndDate())) {

            throw new InvalidOperationException(
                    "Start date cannot be after end date."
            );
        }

        if (budgetRepository.existsByUserAndCategoryAndStartDateAndEndDate(
                user,
                category,
                request.getStartDate(),
                request.getEndDate())) {

            throw new ResourceAlreadyExistsException(
                    "Budget already exists for this category and date range."
            );
        }
    }

}