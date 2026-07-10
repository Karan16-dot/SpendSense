package com.spendsense.recommendation.dto.response;

import com.spendsense.recommendation.entity.RecommendationPriority;
import com.spendsense.recommendation.entity.RecommendationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendationResponse {

    private RecommendationType type;

    private RecommendationPriority priority;

    private String title;

    private String description;

}