package com.spendsense.expense.dto.request;

import com.spendsense.expense.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UpdateExpenseRequest {

    @NotBlank
    private String title;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    private String merchant;

    private String notes;

    @NotNull
    private LocalDate transactionDate;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private UUID categoryId;

}