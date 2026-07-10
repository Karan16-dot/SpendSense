package com.spendsense.dashboard.controller;

import com.spendsense.common.response.ApiResponse;
import com.spendsense.dashboard.dto.response.DashboardResponse;
import com.spendsense.dashboard.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Dashboard APIs")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    @Operation(summary = "Get dashboard")
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard() {

        DashboardResponse response =
                dashboardService.getDashboard();

        return ResponseEntity.ok(
                ApiResponse.<DashboardResponse>builder()
                        .success(true)
                        .message("Dashboard loaded successfully.")
                        .data(response)
                        .build()
        );
    }
}