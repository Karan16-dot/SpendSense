package com.spendsense.insights.service;

import com.spendsense.insights.dto.response.InsightResponse;

import java.util.List;

public interface InsightService {

    List<InsightResponse> generateInsights();

}