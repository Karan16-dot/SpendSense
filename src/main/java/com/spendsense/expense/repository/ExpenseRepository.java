package com.spendsense.expense.repository;

import com.spendsense.expense.entity.Expense;
import com.spendsense.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    /**
     * Get all non-deleted expenses of the logged-in user (Paginated)
     */
    Page<Expense> findByUserAndDeletedFalse(
            User user,
            Pageable pageable
    );

    /**
     * Get a specific expense of the logged-in user
     */
    Optional<Expense> findByIdAndUserAndDeletedFalse(
            UUID id,
            User user
    );

    /**
     * Search expenses by title
     */
    Page<Expense> findByUserAndTitleContainingIgnoreCaseAndDeletedFalse(
            User user,
            String title,
            Pageable pageable
    );

    /**
     * Filter expenses by category
     */
    Page<Expense> findByUserAndCategory_IdAndDeletedFalse(
            User user,
            UUID categoryId,
            Pageable pageable
    );

    /**
     * Filter expenses by transaction date
     */
    Page<Expense> findByUserAndTransactionDateBetweenAndDeletedFalse(
            User user,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

}