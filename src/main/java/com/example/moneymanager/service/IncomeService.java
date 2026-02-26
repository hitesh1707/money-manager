package com.example.moneymanager.service;

import com.example.moneymanager.model.Income;
import com.example.moneymanager.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    private final IncomeRepository repo;

    public IncomeService(IncomeRepository repo) {
        this.repo = repo;
    }

    public Income create(Income income) {
        return repo.save(income);
    }

    public List<Income> getAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}