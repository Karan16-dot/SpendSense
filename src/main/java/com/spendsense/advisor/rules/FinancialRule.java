package com.spendsense.advisor.rules;

import com.spendsense.analytics.dto.response.MonthlySummaryResponse;
import com.spendsense.analytics.dto.response.FinancialHealthResponse;
import com.spendsense.advisor.dto.response.RecommendationResponse;

import java.util.Optional;

public interface FinancialRule {

    Optional<RecommendationResponse> evaluate(
            MonthlySummaryResponse summary,
            FinancialHealthResponse health
    );

}