package com.spendsense.budget.controller;

import com.spendsense.budget.dto.request.CreateBudgetRequest;
import com.spendsense.budget.dto.request.UpdateBudgetRequest;
import com.spendsense.budget.dto.response.BudgetResponse;
import com.spendsense.budget.service.BudgetService;
import com.spendsense.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
@Tag(name = "Budget", description = "Budget Management APIs")
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    @Operation(summary = "Create a new budget")
    public ResponseEntity<ApiResponse<BudgetResponse>> createBudget(
            @Valid @RequestBody CreateBudgetRequest request) {

        BudgetResponse response = budgetService.createBudget(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<BudgetResponse>builder()
                        .success(true)
                        .message("Budget created successfully.")
                        .data(response)
                        .build());
    }

    @GetMapping("/{budgetId}")
    @Operation(summary = "Get budget by ID")
    public ResponseEntity<ApiResponse<BudgetResponse>> getBudget(
            @PathVariable UUID budgetId) {

        BudgetResponse response = budgetService.getBudget(budgetId);

        return ResponseEntity.ok(
                ApiResponse.<BudgetResponse>builder()
                        .success(true)
                        .message("Budget retrieved successfully.")
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    @Operation(summary = "Get all budgets")
    public ResponseEntity<ApiResponse<List<BudgetResponse>>> getAllBudgets() {

        List<BudgetResponse> response =
                budgetService.getAllBudgets();

        return ResponseEntity.ok(
                ApiResponse.<List<BudgetResponse>>builder()
                        .success(true)
                        .message("Budgets retrieved successfully.")
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{budgetId}")
    @Operation(summary = "Update budget")
    public ResponseEntity<ApiResponse<BudgetResponse>> updateBudget(
            @PathVariable UUID budgetId,
            @Valid @RequestBody UpdateBudgetRequest request) {

        BudgetResponse response =
                budgetService.updateBudget(budgetId, request);

        return ResponseEntity.ok(
                ApiResponse.<BudgetResponse>builder()
                        .success(true)
                        .message("Budget updated successfully.")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{budgetId}")
    @Operation(summary = "Delete budget")
    public ResponseEntity<ApiResponse<Void>> deleteBudget(
            @PathVariable UUID budgetId) {

        budgetService.deleteBudget(budgetId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Budget deleted successfully.")
                        .data(null)
                        .build()
        );
    }
}