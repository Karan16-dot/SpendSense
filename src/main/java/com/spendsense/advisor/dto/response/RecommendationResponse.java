package com.spendsense.advisor.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendationResponse {

    private String code;

    private String title;

    private String description;

    private String severity;

}