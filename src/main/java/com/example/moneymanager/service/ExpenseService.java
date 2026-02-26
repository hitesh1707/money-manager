package com.example.moneymanager.service;

import com.example.moneymanager.model.Expense;
import com.example.moneymanager.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    public List<Expense> filter(
            LocalDate startDate,
            LocalDate endDate,
            Long categoryId
    ) {

        if (startDate != null && endDate != null && categoryId != null) {
            return repo.findByDateBetweenAndCategory_Id(startDate, endDate, categoryId);
        }

        if (startDate != null && endDate != null) {
            return repo.findByDateBetween(startDate, endDate);
        }

        if (categoryId != null) {
            return repo.findByCategory_Id(categoryId);
        }

        return repo.findAll();
    }
}