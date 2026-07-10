package com.spendsense.income.dto.request;

import com.spendsense.income.entity.IncomeSource;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateIncomeRequest {

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    @NotNull
    private IncomeSource source;

    @NotNull
    private LocalDate receivedDate;

    @Size(max = 500)
    private String notes;
}