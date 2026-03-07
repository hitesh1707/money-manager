package com.example.moneymanager.service;

import com.example.moneymanager.model.Expense;
import com.example.moneymanager.model.Notification;
import com.example.moneymanager.repository.ExpenseRepository;
import com.example.moneymanager.repository.NotificationRepository;
import com.example.moneymanager.security.SecurityUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repo;
    private final NotificationRepository notificationRepo;

    public ExpenseService(ExpenseRepository repo, NotificationRepository notificationRepo) {
        this.repo             = repo;
        this.notificationRepo = notificationRepo;
    }

    public List<Expense> filter(LocalDate startDate, LocalDate endDate, Long categoryId) {
        String email = SecurityUtils.getCurrentUserEmail();

        if (startDate != null && endDate != null && categoryId != null)
            return repo.findByUserEmailAndDateBetweenAndCategory_Id(email, startDate, endDate, categoryId);
        if (startDate != null && endDate != null)
            return repo.findByUserEmailAndDateBetween(email, startDate, endDate);
        if (categoryId != null)
            return repo.findByUserEmailAndCategory_Id(email, categoryId);

        return repo.findByUserEmail(email);
    }

    public Expense create(Expense expense) {
        expense.setUserEmail(SecurityUtils.getCurrentUserEmail());
        Expense saved = repo.save(expense);

        // ✅ Auto-create notification
        Notification n = new Notification();
        n.setMessage("New expense: ₹" + expense.getAmount() + " for " + expense.getDescription());
        n.setTitle("expense");
        n.setIsRead(false);
        n.setCreatedAt(LocalDateTime.now());
        notificationRepo.save(n);

        return saved;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}