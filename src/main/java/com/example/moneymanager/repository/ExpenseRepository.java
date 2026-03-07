package com.example.moneymanager.repository;

import com.example.moneymanager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserEmail(String userEmail);

    // Update existing filter methods too:
    List<Expense> findByUserEmailAndDateBetween(String userEmail, LocalDate start, LocalDate end);
    List<Expense> findByUserEmailAndCategory_Id(String userEmail, Long categoryId);
    List<Expense> findByUserEmailAndDateBetweenAndCategory_Id(String userEmail, LocalDate start, LocalDate end, Long categoryId);
}