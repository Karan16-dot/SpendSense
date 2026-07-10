package com.spendsense.analytics.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinancialHealthResponse {

    private Integer score;

    private String rating;

    private String recommendation;

}