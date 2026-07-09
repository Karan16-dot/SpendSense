package com.spendsense.expense.service;

import com.spendsense.common.exception.ResourceNotFoundException;
import com.spendsense.expense.dto.request.CreateExpenseRequest;
import com.spendsense.expense.dto.request.ExpenseFilterRequest;
import com.spendsense.expense.dto.request.UpdateExpenseRequest;
import com.spendsense.expense.dto.response.ExpenseResponse;
import com.spendsense.expense.entity.Category;
import com.spendsense.expense.entity.Expense;
import com.spendsense.expense.repository.CategoryRepository;
import com.spendsense.expense.repository.ExpenseRepository;
import com.spendsense.expense.specification.ExpenseSpecification;
import com.spendsense.user.entity.User;
import com.spendsense.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // ---------------- CREATE ----------------

    public ExpenseResponse createExpense(CreateExpenseRequest request) {

        User currentUser = getCurrentUser();

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .merchant(request.getMerchant())
                .notes(request.getNotes())
                .transactionDate(request.getTransactionDate())
                .paymentMethod(request.getPaymentMethod())
                .category(category)
                .user(currentUser)
                .build();

        return mapToResponse(expenseRepository.save(expense));
    }

    // ---------------- GET ALL ----------------

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

        return expenseRepository
                .findByUserAndDeletedFalse(currentUser, pageable)
                .map(this::mapToResponse);
    }

    // ---------------- GET BY ID ----------------

    public ExpenseResponse getExpenseById(UUID expenseId) {

        User currentUser = getCurrentUser();

        Expense expense = expenseRepository
                .findByIdAndUserAndDeletedFalse(expenseId, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        return mapToResponse(expense);
    }

    // ---------------- UPDATE ----------------

    public ExpenseResponse updateExpense(UUID expenseId,
                                         UpdateExpenseRequest request) {

        User currentUser = getCurrentUser();

        Expense expense = expenseRepository
                .findByIdAndUserAndDeletedFalse(expenseId, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setMerchant(request.getMerchant());
        expense.setNotes(request.getNotes());
        expense.setTransactionDate(request.getTransactionDate());
        expense.setPaymentMethod(request.getPaymentMethod());
        expense.setCategory(category);

        return mapToResponse(expenseRepository.save(expense));
    }

    // ---------------- DELETE ----------------

    public void deleteExpense(UUID expenseId) {

        User currentUser = getCurrentUser();

        Expense expense = expenseRepository
                .findByIdAndUserAndDeletedFalse(expenseId, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        expense.setDeleted(true);
        expense.setDeletedAt(LocalDateTime.now());

        expenseRepository.save(expense);
    }

    // ---------------- SEARCH ----------------

    public Page<ExpenseResponse> searchExpenses(
            ExpenseFilterRequest filter,
            int page,
            int size,
            String sortBy,
            String direction) {

        User currentUser = getCurrentUser();

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Expense> specification =
                ExpenseSpecification.filterExpenses(filter, currentUser);

        return expenseRepository
                .findAll(specification, pageable)
                .map(this::mapToResponse);
    }

    // ---------------- CURRENT USER ----------------

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    // ---------------- MAPPER ----------------

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