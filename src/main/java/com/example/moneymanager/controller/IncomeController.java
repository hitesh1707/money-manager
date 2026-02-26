package com.example.moneymanager.controller;

import com.example.moneymanager.model.Income;
import com.example.moneymanager.service.IncomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

    private final IncomeService service;

    public IncomeController(IncomeService service) {
        this.service = service;
    }

    @PostMapping
    public Income create(@RequestBody Income income) {
        return service.create(income);
    }

    @GetMapping
    public List<Income> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}