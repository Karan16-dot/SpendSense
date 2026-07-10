package com.spendsense.budget.repository;

import com.spendsense.budget.entity.Budget;
import com.spendsense.category.entity.Category;
import com.spendsense.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    Optional<Budget> findByIdAndUser(UUID id, User user);

    List<Budget> findByUserOrderByStartDateDesc(User user);

    boolean existsByUserAndCategoryAndStartDateAndEndDate(
            User user,
            Category category,
            LocalDate startDate,
            LocalDate endDate
    );

}