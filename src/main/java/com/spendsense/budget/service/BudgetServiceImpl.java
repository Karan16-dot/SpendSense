package com.spendsense.budget.service;

import com.spendsense.budget.calculation.BudgetCalculationService;
import com.spendsense.budget.dto.request.CreateBudgetRequest;
import com.spendsense.budget.dto.request.UpdateBudgetRequest;
import com.spendsense.budget.dto.response.BudgetResponse;
import com.spendsense.budget.entity.Budget;
import com.spendsense.budget.entity.BudgetStatus;
import com.spendsense.budget.mapper.BudgetMapper;
import com.spendsense.budget.repository.BudgetRepository;
import com.spendsense.budget.validator.BudgetValidator;
import com.spendsense.category.entity.Category;
import com.spendsense.category.repository.CategoryRepository;
import com.spendsense.common.exception.ResourceNotFoundException;
import com.spendsense.common.security.CurrentUserService;
import com.spendsense.expense.repository.ExpenseRepository;
import com.spendsense.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final BudgetMapper budgetMapper;
    private final BudgetValidator budgetValidator;
    private final BudgetCalculationService calculationService;
    private final CurrentUserService currentUserService;

    @Override
    public BudgetResponse createBudget(CreateBudgetRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found."));

        budgetValidator.validate(request, currentUser, category);

        Budget budget = budgetMapper.toEntity(request);

        budget.setUser(currentUser);
        budget.setCategory(category);

        Budget savedBudget = budgetRepository.save(budget);

        return buildBudgetResponse(savedBudget);
    }

    @Override
    @Transactional(readOnly = true)
    public BudgetResponse getBudget(UUID budgetId) {

        User currentUser = currentUserService.getCurrentUser();

        Budget budget = budgetRepository
                .findByIdAndUser(budgetId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found."));

        return buildBudgetResponse(budget);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BudgetResponse> getAllBudgets() {

        User currentUser = currentUserService.getCurrentUser();

        return budgetRepository
                .findByUserOrderByStartDateDesc(currentUser)
                .stream()
                .map(this::buildBudgetResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BudgetResponse updateBudget(
            UUID budgetId,
            UpdateBudgetRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Budget budget = budgetRepository
                .findByIdAndUser(budgetId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found."));

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found."));

        budgetMapper.updateEntity(request, budget);

        budget.setCategory(category);

        Budget updatedBudget = budgetRepository.save(budget);

        return buildBudgetResponse(updatedBudget);
    }

    @Override
    public void deleteBudget(UUID budgetId) {

        User currentUser = currentUserService.getCurrentUser();

        Budget budget = budgetRepository
                .findByIdAndUser(budgetId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found."));

        budgetRepository.delete(budget);
    }
    /**
     * Builds a fully populated BudgetResponse with calculated fields.
     */
    private BudgetResponse buildBudgetResponse(Budget budget) {

        BigDecimal spentAmount = expenseRepository.calculateSpentAmount(
                budget.getUser(),
                budget.getCategory(),
                budget.getStartDate(),
                budget.getEndDate()
        );

        BigDecimal remainingAmount =
                calculationService.calculateRemainingAmount(
                        budget.getAmount(),
                        spentAmount
                );

        double usagePercentage =
                calculationService.calculateUsagePercentage(
                        budget.getAmount(),
                        spentAmount
                );

        BudgetStatus status =
                calculationService.determineStatus(
                        usagePercentage,
                        budget.getAlertPercentage()
                );

        BudgetResponse response = budgetMapper.toResponse(budget);

        response.setSpentAmount(spentAmount);
        response.setRemainingAmount(remainingAmount);
        response.setUsagePercentage(usagePercentage);
        response.setStatus(status);

        return response;
    }

}