package com.spendsense.analytics.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MonthlySummaryResponse {

    private BigDecimal totalIncome;

    private BigDecimal totalExpense;

    private BigDecimal totalSavings;

    private Double savingsRate;

    private Integer totalTransactions;

}