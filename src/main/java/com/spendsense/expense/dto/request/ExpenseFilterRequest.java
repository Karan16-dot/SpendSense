package com.spendsense.expense.dto.request;

import com.spendsense.expense.enums.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ExpenseFilterRequest {

    private String keyword;

    private UUID categoryId;

    private PaymentMethod paymentMethod;

    private String merchant;

    private LocalDate fromDate;

    private LocalDate toDate;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;
}