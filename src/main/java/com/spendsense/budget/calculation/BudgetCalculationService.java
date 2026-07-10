package com.spendsense.budget.calculation;

import com.spendsense.budget.entity.BudgetStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BudgetCalculationService {

    /**
     * Calculate remaining budget.
     */
    public BigDecimal calculateRemainingAmount(
            BigDecimal allocated,
            BigDecimal spent) {

        return allocated.subtract(spent);
    }

    /**
     * Calculate percentage used.
     */
    public double calculateUsagePercentage(
            BigDecimal allocated,
            BigDecimal spent) {

        if (allocated.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        }

        return spent
                .multiply(BigDecimal.valueOf(100))
                .divide(allocated, 2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * Determine budget status.
     */
    public BudgetStatus determineStatus(
            double percentage,
            int alertPercentage) {

        if (percentage >= 100) {
            return BudgetStatus.OVER_BUDGET;
        }

        if (percentage >= alertPercentage) {
            return BudgetStatus.NEAR_LIMIT;
        }

        return BudgetStatus.UNDER_BUDGET;
    }

}