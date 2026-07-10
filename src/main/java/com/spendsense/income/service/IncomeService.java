package com.spendsense.income.service;

import com.spendsense.income.dto.request.CreateIncomeRequest;
import com.spendsense.income.dto.request.UpdateIncomeRequest;
import com.spendsense.income.dto.response.IncomeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IncomeService {

    IncomeResponse createIncome(CreateIncomeRequest request);

    IncomeResponse updateIncome(UUID incomeId,
                                UpdateIncomeRequest request);

    IncomeResponse getIncome(UUID incomeId);

    Page<IncomeResponse> getAllIncome(Pageable pageable);

    void deleteIncome(UUID incomeId);

}