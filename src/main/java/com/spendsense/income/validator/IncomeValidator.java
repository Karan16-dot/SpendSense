package com.spendsense.income.validator;

import com.spendsense.common.exception.InvalidOperationException;
import com.spendsense.income.dto.request.CreateIncomeRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IncomeValidator {

    public void validate(CreateIncomeRequest request) {

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {

            throw new InvalidOperationException(
                    "Income amount must be greater than zero."
            );
        }

    }

}