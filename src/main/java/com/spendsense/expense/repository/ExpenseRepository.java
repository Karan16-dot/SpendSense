package com.spendsense.expense.repository;

import com.spendsense.expense.entity.Expense;
import com.spendsense.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository extends
        JpaRepository<Expense, UUID>,
        JpaSpecificationExecutor<Expense> {

    /**
     * Get all non-deleted expenses of the logged-in user.
     */
    Page<Expense> findByUserAndDeletedFalse(
            User user,
            Pageable pageable
    );

    /**
     * Get one expense by id for the logged-in user.
     */
    Optional<Expense> findByIdAndUserAndDeletedFalse(
            UUID id,
            User user
    );

    /**
     * Search expenses by title.
     */
    Page<Expense> findByUserAndTitleContainingIgnoreCaseAndDeletedFalse(
            User user,
            String keyword,
            Pageable pageable
    );

    /**
     * Filter by category.
     */
    Page<Expense> findByUserAndCategory_IdAndDeletedFalse(
            User user,
            UUID categoryId,
            Pageable pageable
    );

    /**
     * Filter by date range.
     */
    Page<Expense> findByUserAndTransactionDateBetweenAndDeletedFalse(
            User user,
            LocalDate fromDate,
            LocalDate toDate,
            Pageable pageable
    );
}