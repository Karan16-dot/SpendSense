package com.spendsense.budget.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UpdateBudgetRequest {

    @NotBlank(message = "Budget name is required")
    @Size(max = 100)
    private String name;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Min(1)
    @Max(100)
    private Integer alertPercentage;

    @NotNull(message = "Category is required")
    private UUID categoryId;

}