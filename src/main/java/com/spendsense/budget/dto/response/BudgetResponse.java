package com.spendsense.budget.dto.response;

import lombok.Builder;
import lombok.Data;


import com.spendsense.budget.entity.BudgetStatus;

private BudgetStatus status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class BudgetResponse {

    private UUID id;

    private String name;

    private BigDecimal allocatedAmount;

    private BigDecimal spentAmount;

    private BigDecimal remainingAmount;

    private Double usagePercentage;

    private String status;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer alertPercentage;

    private UUID categoryId;

    private String categoryName;

}