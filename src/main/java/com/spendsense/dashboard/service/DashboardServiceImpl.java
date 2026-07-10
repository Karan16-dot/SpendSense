package com.spendsense.dashboard.service;

import com.spendsense.analytics.dto.response.CashFlowResponse;
import com.spendsense.analytics.dto.response.FinancialHealthResponse;
import com.spendsense.analytics.dto.response.MonthlySummaryResponse;
import com.spendsense.analytics.service.AnalyticsService;
import com.spendsense.dashboard.dto.response.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final AnalyticsService analyticsService;

    @Override
    public DashboardResponse getDashboard() {

        YearMonth currentMonth = YearMonth.now();

        MonthlySummaryResponse monthlySummary =
                analyticsService.getMonthlySummary(currentMonth);

        CashFlowResponse cashFlow =
                analyticsService.getCashFlow(currentMonth);

        FinancialHealthResponse health =
                analyticsService.getFinancialHealth();

        return DashboardResponse.builder()
                .monthlySummary(monthlySummary)
                .cashFlow(cashFlow)
                .financialHealth(health)
                .build();
    }
}