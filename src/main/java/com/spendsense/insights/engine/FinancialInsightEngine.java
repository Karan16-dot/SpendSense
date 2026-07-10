package com.spendsense.insights.engine;

import com.spendsense.analytics.dto.response.FinancialHealthResponse;
import com.spendsense.insights.dto.response.InsightResponse;
import com.spendsense.insights.entity.InsightType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FinancialInsightEngine {

    public List<InsightResponse> generate(
            FinancialHealthResponse health) {

        List<InsightResponse> insights = new ArrayList<>();

        if (health.getScore() >= 90) {

            insights.add(
                    InsightResponse.builder()
                            .type(InsightType.SUCCESS)
                            .title("Excellent Financial Health")
                            .description(
                                    "Keep following your current financial habits."
                            )
                            .build()
            );
        }

        else if (health.getScore() >= 75) {

            insights.add(
                    InsightResponse.builder()
                            .type(InsightType.INFO)
                            .title("Strong Financial Position")
                            .description(
                                    "Increase investments to grow long-term wealth."
                            )
                            .build()
            );
        }

        else if (health.getScore() >= 50) {

            insights.add(
                    InsightResponse.builder()
                            .type(InsightType.WARNING)
                            .title("Savings Need Improvement")
                            .description(
                                    "Try increasing your monthly savings by at least 10%."
                            )
                            .build()
            );
        }

        else {

            insights.add(
                    InsightResponse.builder()
                            .type(InsightType.DANGER)
                            .title("Financial Risk Detected")
                            .description(
                                    "Your expenses are high compared to your income."
                            )
                            .build()
            );
        }

        return insights;
    }

}