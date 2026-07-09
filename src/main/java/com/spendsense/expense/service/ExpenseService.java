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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    /**
     * Create Expense
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
     * Get Logged-in User Expenses
     */
    public Page<ExpenseResponse> getMyExpenses(
            int page,
            int size,
            String sortBy,
            String direction) {

        User currentUser = getCurrentUser();

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Expense> expenses =
                expenseRepository.findByUser(currentUser, pageable);

        return expenses.map(this::mapToResponse);
    }

    /**
     * Returns currently authenticated user
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
     * Entity → DTO
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