package com.example.moneymanager.controller;

import com.example.moneymanager.model.Expense;
import com.example.moneymanager.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/expenses")
@CrossOrigin(origins = "http://localhost:8080")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping("/filter")
    public List<Expense> filter(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) Long categoryId
    ) {
        return service.filter(startDate, endDate, categoryId);
    }
}