package com.spendsense.analytics.service;

import com.spendsense.analytics.dto.response.CashFlowResponse;
import com.spendsense.analytics.dto.response.FinancialHealthResponse;
import com.spendsense.analytics.dto.response.MonthlySummaryResponse;

import java.time.YearMonth;

public interface AnalyticsService {

    MonthlySummaryResponse getMonthlySummary(
            YearMonth month);

    CashFlowResponse getCashFlow(
            YearMonth month);

    FinancialHealthResponse getFinancialHealth();

}