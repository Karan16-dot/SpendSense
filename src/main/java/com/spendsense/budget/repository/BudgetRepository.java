package com.spendsense.budget.repository;

import com.spendsense.budget.entity.Budget;
import com.spendsense.category.entity.Category;
import com.spendsense.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    List<Budget> findByUser(User user);

    List<Budget> findByCategory(Category category);

    List<Budget> findByUserAndCategory(User user, Category category);

}