package com.spendsense.analytics.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FinancialProfileResponse {

    private BigDecimal monthlyIncome;

    private BigDecimal monthlyExpense;

    private BigDecimal monthlySavings;

    private Double savingsRate;

    private Integer financialHealthScore;

    private String financialHealthRating;

    private Integer activeBudgets;

    private Integer totalTransactions;

}