package com.spendsense.expense.controller;

import com.spendsense.expense.dto.request.CreateExpenseRequest;
import com.spendsense.expense.dto.response.ExpenseResponse;
import com.spendsense.expense.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(
            @Valid @RequestBody CreateExpenseRequest request) {

        ExpenseResponse response =
                expenseService.createExpense(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}