package com.spendsense.expense.repository;

import com.spendsense.expense.entity.Expense;
import com.spendsense.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepository
        extends JpaRepository<Expense, UUID> {

    List<Expense> findByUser(User user);
}