package com.spendsense.budget.service;

import com.spendsense.budget.dto.request.CreateBudgetRequest;
import com.spendsense.budget.dto.request.UpdateBudgetRequest;
import com.spendsense.budget.dto.response.BudgetResponse;

import java.util.List;
import java.util.UUID;

public interface BudgetService {

    BudgetResponse createBudget(CreateBudgetRequest request);

    BudgetResponse updateBudget(
            UUID budgetId,
            UpdateBudgetRequest request);

    BudgetResponse getBudget(UUID budgetId);

    List<BudgetResponse> getAllBudgets();

    void deleteBudget(UUID budgetId);

}