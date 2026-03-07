package com.example.moneymanager.repository;

import com.example.moneymanager.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserEmail(String userEmail);
}