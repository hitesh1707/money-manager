package com.example.moneymanager.repository;

import com.example.moneymanager.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}