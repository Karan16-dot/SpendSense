package com.spendsense.expense.controller;

import com.spendsense.expense.dto.request.CreateExpenseRequest;
import com.spendsense.expense.dto.request.ExpenseFilterRequest;
import com.spendsense.expense.dto.request.UpdateExpenseRequest;
import com.spendsense.expense.dto.response.ExpenseResponse;
import com.spendsense.expense.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(
            @Valid @RequestBody CreateExpenseRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseService.createExpense(request));
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseResponse>> getExpenses(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "transactionDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        return ResponseEntity.ok(
                expenseService.getMyExpenses(page, size, sortBy, direction)
        );
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpenseById(
            @PathVariable UUID expenseId) {

        return ResponseEntity.ok(
                expenseService.getExpenseById(expenseId)
        );
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(
            @PathVariable UUID expenseId,
            @Valid @RequestBody UpdateExpenseRequest request) {

        return ResponseEntity.ok(
                expenseService.updateExpense(expenseId, request)
        );
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable UUID expenseId) {

        expenseService.deleteExpense(expenseId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ExpenseResponse>> filterExpenses(

            ExpenseFilterRequest request,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "transactionDate") String sortBy,

            @RequestParam(defaultValue = "desc") String direction) {

        return ResponseEntity.ok(
                expenseService.searchExpenses(
                        request,
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }
}