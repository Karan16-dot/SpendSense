package com.spendsense.expense.repository;

import com.spendsense.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepository
        extends JpaRepository<Expense, UUID> {
}