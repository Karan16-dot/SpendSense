package com.spendsense.analytics.service;

import com.spendsense.analytics.calculation.FinancialHealthCalculator;
import com.spendsense.analytics.dto.response.CashFlowResponse;
import com.spendsense.analytics.dto.response.FinancialHealthResponse;
import com.spendsense.analytics.dto.response.MonthlySummaryResponse;
import com.spendsense.common.security.CurrentUserService;
import com.spendsense.expense.repository.ExpenseRepository;
import com.spendsense.income.repository.IncomeRepository;
import com.spendsense.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalyticsServiceImpl implements AnalyticsService {

    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;
    private final CurrentUserService currentUserService;
    private final FinancialHealthCalculator financialHealthCalculator;

    @Override
    public MonthlySummaryResponse getMonthlySummary(
            YearMonth month) {

        User currentUser = currentUserService.getCurrentUser();

        var startDate = month.atDay(1);
        var endDate = month.atEndOfMonth();

        BigDecimal income =
                incomeRepository.calculateTotalIncomeBetween(
                        currentUser,
                        startDate,
                        endDate
                );

        BigDecimal expense =
                expenseRepository.calculateTotalExpense(
                        currentUser,
                        startDate,
                        endDate
                );

        BigDecimal savings =
                income.subtract(expense);

        double savingsRate = 0;

        if (income.compareTo(BigDecimal.ZERO) > 0) {

            savingsRate = savings
                    .multiply(BigDecimal.valueOf(100))
                    .divide(income, 2, RoundingMode.HALF_UP)
                    .doubleValue();
        }

        long transactionCount =
                incomeRepository.countByUserAndDeletedFalse(currentUser)
                        + expenseRepository.countByUserAndDeletedFalse(currentUser);

        return MonthlySummaryResponse.builder()
                .totalIncome(income)
                .totalExpense(expense)
                .totalSavings(savings)
                .savingsRate(savingsRate)
                .totalTransactions((int) transactionCount)
                .build();
    }

    @Override
    public CashFlowResponse getCashFlow(YearMonth month) {

        User currentUser = currentUserService.getCurrentUser();

        var startDate = month.atDay(1);
        var endDate = month.atEndOfMonth();

        BigDecimal income = incomeRepository.calculateTotalIncomeBetween(
                currentUser,
                startDate,
                endDate
        );

        BigDecimal expense = expenseRepository.calculateTotalExpense(
                currentUser,
                startDate,
                endDate
        );

        BigDecimal balance = income.subtract(expense);

        return CashFlowResponse.builder()
                .inflow(income)
                .outflow(expense)
                .balance(balance)
                .build();
    }

    @Override
    public FinancialHealthResponse getFinancialHealth() {

        User currentUser = currentUserService.getCurrentUser();

        BigDecimal totalIncome =
                incomeRepository.calculateTotalIncome(currentUser);

        BigDecimal totalExpense =
                expenseRepository.calculateTotalExpense(
                        currentUser,
                        java.time.LocalDate.of(2000, 1, 1),
                        java.time.LocalDate.now()
                );

        int score = financialHealthCalculator
                .calculateScore(totalIncome, totalExpense);

        return FinancialHealthResponse.builder()
                .score(score)
                .rating(
                        financialHealthCalculator.rating(score)
                )
                .recommendation(
                        financialHealthCalculator.recommendation(score)
                )
                .build();
    }
}