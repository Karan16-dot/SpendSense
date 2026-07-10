package com.spendsense.income.controller;

import com.spendsense.common.response.ApiResponse;
import com.spendsense.income.dto.request.CreateIncomeRequest;
import com.spendsense.income.dto.request.UpdateIncomeRequest;
import com.spendsense.income.dto.response.IncomeResponse;
import com.spendsense.income.service.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
@Tag(name = "Income", description = "Income Management APIs")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    @Operation(summary = "Create income")
    public ResponseEntity<ApiResponse<IncomeResponse>> createIncome(
            @Valid @RequestBody CreateIncomeRequest request) {

        IncomeResponse response = incomeService.createIncome(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<IncomeResponse>builder()
                        .success(true)
                        .message("Income created successfully.")
                        .data(response)
                        .build());
    }

    @GetMapping("/{incomeId}")
    @Operation(summary = "Get income by id")
    public ResponseEntity<ApiResponse<IncomeResponse>> getIncome(
            @PathVariable UUID incomeId) {

        return ResponseEntity.ok(
                ApiResponse.<IncomeResponse>builder()
                        .success(true)
                        .message("Income retrieved successfully.")
                        .data(incomeService.getIncome(incomeId))
                        .build());
    }

    @GetMapping
    @Operation(summary = "Get all income")
    public ResponseEntity<ApiResponse<Page<IncomeResponse>>> getAllIncome(
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.<Page<IncomeResponse>>builder()
                        .success(true)
                        .message("Income list retrieved successfully.")
                        .data(incomeService.getAllIncome(pageable))
                        .build());
    }

    @PutMapping("/{incomeId}")
    @Operation(summary = "Update income")
    public ResponseEntity<ApiResponse<IncomeResponse>> updateIncome(
            @PathVariable UUID incomeId,
            @Valid @RequestBody UpdateIncomeRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<IncomeResponse>builder()
                        .success(true)
                        .message("Income updated successfully.")
                        .data(incomeService.updateIncome(incomeId, request))
                        .build());
    }

    @DeleteMapping("/{incomeId}")
    @Operation(summary = "Delete income")
    public ResponseEntity<ApiResponse<Void>> deleteIncome(
            @PathVariable UUID incomeId) {

        incomeService.deleteIncome(incomeId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Income deleted successfully.")
                        .build());
    }
}