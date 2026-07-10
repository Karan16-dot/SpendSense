package com.spendsense.income.dto.response;

import com.spendsense.income.entity.IncomeSource;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class IncomeResponse {

    private UUID id;

    private String title;

    private BigDecimal amount;

    private IncomeSource source;

    private LocalDate receivedDate;

    private String notes;
}