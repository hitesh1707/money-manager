package com.example.moneymanager.service;

import com.example.moneymanager.dto.DashboardResponse;
import com.example.moneymanager.model.Expense;
import com.example.moneymanager.model.Income;
import com.example.moneymanager.repository.ExpenseRepository;
import com.example.moneymanager.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.time.format.TextStyle;
import java.util.*;

@Service
public class DashboardService {

    private final IncomeRepository incomeRepo;
    private final ExpenseRepository expenseRepo;

    public DashboardService(IncomeRepository incomeRepo,
                            ExpenseRepository expenseRepo) {
        this.incomeRepo = incomeRepo;
        this.expenseRepo = expenseRepo;
    }

    public DashboardResponse getDashboard() {

        DashboardResponse response = new DashboardResponse();

        List<Income> incomes = incomeRepo.findAll();
        List<Expense> expenses = expenseRepo.findAll();

        // ---------------- TOTAL INCOME ----------------
        double totalIncome = incomes.stream()
                .mapToDouble(Income::getAmount)
                .sum();

        // ---------------- TOTAL EXPENSE ----------------
        double totalExpense = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        // ---------------- BALANCE ----------------
        double balance = totalIncome - totalExpense;

        // ---------------- EXPENSE BY CATEGORY ----------------
        Map<String, Double> expenseByCategory = new HashMap<>();

        for (Expense e : expenses) {
            String categoryName = e.getCategory().getName();
            expenseByCategory.put(
                    categoryName,
                    expenseByCategory.getOrDefault(categoryName, 0.0) + e.getAmount()
            );
        }

        // ---------------- MONTHLY EXPENSE ----------------
        Map<String, Double> monthlyExpense = new HashMap<>();

        for (Expense e : expenses) {
            String month = e.getDate()
                    .getMonth()
                    .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

            monthlyExpense.put(
                    month,
                    monthlyExpense.getOrDefault(month, 0.0) + e.getAmount()
            );
        }

        // set response
        response.setTotalIncome(totalIncome);
        response.setTotalExpense(totalExpense);
        response.setBalance(balance);
        response.setExpenseByCategory(expenseByCategory);
        response.setMonthlyExpense(monthlyExpense);

        return response;
    }
}