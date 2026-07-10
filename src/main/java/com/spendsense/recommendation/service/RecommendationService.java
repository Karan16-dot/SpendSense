package com.spendsense.recommendation.service;

import com.spendsense.recommendation.dto.response.RecommendationResponse;

import java.util.List;

public interface RecommendationService {

    List<RecommendationResponse> generateRecommendations();

}