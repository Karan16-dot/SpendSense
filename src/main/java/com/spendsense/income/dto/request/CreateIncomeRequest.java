package com.spendsense.income.dto.request;

import com.spendsense.income.entity.IncomeSource;
import jakarta.validation.constraints.*;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateIncomeRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 150)
    private String title;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    @NotNull(message = "Income source is required")
    private IncomeSource source;

    @NotNull(message = "Received date is required")
    private LocalDate receivedDate;

    @Size(max = 500)
    private String notes;
}