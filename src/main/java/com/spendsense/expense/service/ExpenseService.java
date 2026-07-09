package com.spendsense.expense.service;

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

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ExpenseResponse createExpense(CreateExpenseRequest request) {

        // Get the currently authenticated user
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        // Find user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Find category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        // Create expense
        Expense expense = Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .merchant(request.getMerchant())
                .notes(request.getNotes())
                .transactionDate(request.getTransactionDate())
                .paymentMethod(request.getPaymentMethod())
                .user(user)
                .category(category)
                .build();

        // Save expense
        Expense savedExpense = expenseRepository.save(expense);

        // Return response
        return ExpenseResponse.builder()
                .id(savedExpense.getId())
                .title(savedExpense.getTitle())
                .amount(savedExpense.getAmount())
                .merchant(savedExpense.getMerchant())
                .notes(savedExpense.getNotes())
                .transactionDate(savedExpense.getTransactionDate())
                .paymentMethod(savedExpense.getPaymentMethod())
                .category(savedExpense.getCategory().getName())
                .build();
    }
}