package com.example.moneymanager.service;

import com.example.moneymanager.model.Category;
import com.example.moneymanager.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public Category create(Category category) {
        return repo.save(category);
    }

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Category getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category update(Long id, Category newData) {

        Category category = getById(id);

        category.setName(newData.getName());
        category.setDescription(newData.getDescription());

        return repo.save(category);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}