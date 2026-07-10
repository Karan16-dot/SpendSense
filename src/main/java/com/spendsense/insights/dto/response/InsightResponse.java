package com.spendsense.insights.dto.response;

import com.spendsense.insights.entity.InsightType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsightResponse {

    private InsightType type;

    private String title;

    private String description;

}