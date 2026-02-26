package com.example.moneymanager.repository;

import com.example.moneymanager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Expense> findByCategory_Id(Long categoryId);

    List<Expense> findByDateBetweenAndCategory_Id(
            LocalDate startDate,
            LocalDate endDate,
            Long categoryId
    );
}