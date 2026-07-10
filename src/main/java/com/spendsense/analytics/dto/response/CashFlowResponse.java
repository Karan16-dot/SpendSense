package com.spendsense.analytics.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CashFlowResponse {

    private BigDecimal inflow;

    private BigDecimal outflow;

    private BigDecimal balance;

}