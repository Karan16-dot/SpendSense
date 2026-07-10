package com.spendsense.analytics.calculation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class FinancialHealthCalculator {

    public int calculateScore(
            BigDecimal income,
            BigDecimal expense) {

        if (income.compareTo(BigDecimal.ZERO) <= 0) {
            return 0;
        }

        BigDecimal savings = income.subtract(expense);

        double savingsRate = savings
                .multiply(BigDecimal.valueOf(100))
                .divide(income, 2, RoundingMode.HALF_UP)
                .doubleValue();

        int score = 50;

        if (savingsRate >= 40)
            score += 30;
        else if (savingsRate >= 25)
            score += 20;
        else if (savingsRate >= 10)
            score += 10;

        double expenseRatio = expense
                .multiply(BigDecimal.valueOf(100))
                .divide(income, 2, RoundingMode.HALF_UP)
                .doubleValue();

        if (expenseRatio <= 50)
            score += 20;
        else if (expenseRatio <= 70)
            score += 10;

        return Math.min(score, 100);
    }

    public String rating(int score) {

        if (score >= 90)
            return "Excellent";

        if (score >= 75)
            return "Very Good";

        if (score >= 60)
            return "Good";

        if (score >= 40)
            return "Needs Improvement";

        return "Poor";
    }

    public String recommendation(int score) {

        if (score >= 90)
            return "Maintain your financial habits.";

        if (score >= 75)
            return "Increase long-term investments.";

        if (score >= 60)
            return "Improve monthly savings.";

        if (score >= 40)
            return "Reduce unnecessary expenses.";

        return "Increase income and reduce spending.";
    }

}