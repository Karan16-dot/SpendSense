package com.spendsense.dashboard.dto.response;

import com.spendsense.analytics.dto.response.CashFlowResponse;
import com.spendsense.analytics.dto.response.FinancialHealthResponse;
import com.spendsense.analytics.dto.response.MonthlySummaryResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private MonthlySummaryResponse monthlySummary;

    private CashFlowResponse cashFlow;

    private FinancialHealthResponse financialHealth;

}