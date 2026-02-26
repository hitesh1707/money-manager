package com.example.moneymanager.dto;

import lombok.Data;
import java.util.Map;

@Data
public class DashboardResponse {

    private Double totalIncome;
    private Double totalExpense;
    private Double balance;

    private Map<String, Double> expenseByCategory;
    private Map<String, Double> monthlyExpense;

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Map<String, Double> getExpenseByCategory() {
        return expenseByCategory;
    }

    public void setExpenseByCategory(Map<String, Double> expenseByCategory) {
        this.expenseByCategory = expenseByCategory;
    }

    public Map<String, Double> getMonthlyExpense() {
        return monthlyExpense;
    }

    public void setMonthlyExpense(Map<String, Double> monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }
}