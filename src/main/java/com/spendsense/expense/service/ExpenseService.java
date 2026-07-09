package com.spendsense.expense.service;

import com.spendsense.common.exception.ResourceNotFoundException;
import com.spendsense.expense.dto.request.CreateExpenseRequest;
import com.spendsense.expense.dto.response.ExpenseResponse;
import com.spendsense.expense.entity.Category;
import com.spendsense.expense.entity.Expense;
import com.spendsense.expense.repository.CategoryRepository;
import com.spendsense.expense.repository.ExpenseRepository;
import com.spendsense.user.entity.User;
import com.spendsense.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    /**
     * Creates a new expense for the currently authenticated user.
     */
    public ExpenseResponse createExpense(CreateExpenseRequest request) {

        User currentUser = getCurrentUser();

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .merchant(request.getMerchant())
                .notes(request.getNotes())
                .transactionDate(request.getTransactionDate())
                .paymentMethod(request.getPaymentMethod())
                .user(currentUser)
                .category(category)
                .build();

        Expense savedExpense = expenseRepository.save(expense);

        return mapToResponse(savedExpense);
    }

    /**
     * Returns all expenses of the currently authenticated user.
     */
    public List<ExpenseResponse> getMyExpenses() {

        User currentUser = getCurrentUser();

        List<Expense> expenses = expenseRepository.findByUser(currentUser);

        return expenses.stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Retrieves the currently authenticated user from Spring Security.
     */
    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    /**
     * Converts an Expense entity into an ExpenseResponse DTO.
     */
    private ExpenseResponse mapToResponse(Expense expense) {

        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .merchant(expense.getMerchant())
                .notes(expense.getNotes())
                .transactionDate(expense.getTransactionDate())
                .paymentMethod(expense.getPaymentMethod())
                .category(expense.getCategory().getName())
                .build();
    }
}