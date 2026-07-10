package com.spendsense.analytics.controller;

import com.spendsense.analytics.dto.response.CashFlowResponse;
import com.spendsense.analytics.dto.response.FinancialHealthResponse;
import com.spendsense.analytics.dto.response.MonthlySummaryResponse;
import com.spendsense.analytics.service.AnalyticsService;
import com.spendsense.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@Tag(name = "Analytics", description = "Financial Analytics APIs")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/monthly-summary")
    @Operation(summary = "Get monthly financial summary")
    public ResponseEntity<ApiResponse<MonthlySummaryResponse>>
    getMonthlySummary(
            @RequestParam int year,
            @RequestParam int month) {

        MonthlySummaryResponse response =
                analyticsService.getMonthlySummary(
                        YearMonth.of(year, month));

        return ResponseEntity.ok(
                ApiResponse.<MonthlySummaryResponse>builder()
                        .success(true)
                        .message("Monthly summary retrieved successfully.")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/cash-flow")
    @Operation(summary = "Get monthly cash flow")
    public ResponseEntity<ApiResponse<CashFlowResponse>>
    getCashFlow(
            @RequestParam int year,
            @RequestParam int month) {

        CashFlowResponse response =
                analyticsService.getCashFlow(
                        YearMonth.of(year, month));

        return ResponseEntity.ok(
                ApiResponse.<CashFlowResponse>builder()
                        .success(true)
                        .message("Cash flow retrieved successfully.")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/financial-health")
    @Operation(summary = "Get financial health score")
    public ResponseEntity<ApiResponse<FinancialHealthResponse>>
    getFinancialHealth() {

        FinancialHealthResponse response =
                analyticsService.getFinancialHealth();

        return ResponseEntity.ok(
                ApiResponse.<FinancialHealthResponse>builder()
                        .success(true)
                        .message("Financial health retrieved successfully.")
                        .data(response)
                        .build()
        );
    }
}