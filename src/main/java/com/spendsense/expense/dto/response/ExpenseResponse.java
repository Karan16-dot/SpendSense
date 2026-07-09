package com.spendsense.expense.dto.response;

import com.spendsense.expense.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ExpenseResponse {

    private UUID id;

    private String title;

    private BigDecimal amount;

    private String merchant;

    private String notes;

    private LocalDate transactionDate;

    private PaymentMethod paymentMethod;

    private String category;

}