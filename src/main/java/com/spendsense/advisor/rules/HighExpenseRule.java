package com.spendsense.advisor.rules;

import com.spendsense.advisor.dto.response.RecommendationResponse;
import com.spendsense.analytics.dto.response.FinancialHealthResponse;
import com.spendsense.analytics.dto.response.MonthlySummaryResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HighExpenseRule implements FinancialRule {

    @Override
    public Optional<RecommendationResponse> evaluate(
            MonthlySummaryResponse summary,
            FinancialHealthResponse health) {

        if (summary.getSavingsRate() != null &&
                summary.getSavingsRate() < 10) {

            return Optional.of(
                    RecommendationResponse.builder()
                            .code("LOW_SAVINGS")
                            .title("Low Savings Rate")
                            .description(
                                    "Your monthly savings rate is below 10%. Consider reducing discretionary expenses."
                            )
                            .severity("HIGH")
                            .build()
            );
        }

        return Optional.empty();
    }
}