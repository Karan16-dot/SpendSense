package com.spendsense.income.calculation;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IncomeCalculationService {

    public BigDecimal calculateTotalIncome(List<BigDecimal> incomeList) {

        return incomeList.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}