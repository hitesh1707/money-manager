package com.example.moneymanager.service;

import com.example.moneymanager.model.Income;
import com.example.moneymanager.model.Notification;
import com.example.moneymanager.repository.IncomeRepository;
import com.example.moneymanager.repository.NotificationRepository;
import com.example.moneymanager.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncomeService {

    private final IncomeRepository repo;
    private final NotificationRepository notificationRepo;

    public IncomeService(IncomeRepository repo, NotificationRepository notificationRepo) {
        this.repo             = repo;
        this.notificationRepo = notificationRepo;
    }

    public Income create(Income income) {
        income.setUserEmail(SecurityUtils.getCurrentUserEmail());
        Income saved = repo.save(income);

        // ✅ Notify on new income
        Notification n = new Notification();
        n.setMessage("New income added: ₹" + income.getAmount() + " from " + income.getSource());
        n.setTitle("income");
        n.setIsRead(false);
        n.setCreatedAt(LocalDateTime.now());
        notificationRepo.save(n);

        return saved;
    }

    public List<Income> getAll() {
        return repo.findByUserEmail(SecurityUtils.getCurrentUserEmail());
    }

    public void delete(Long id) {
        // ✅ Notify on delete
        repo.findById(id).ifPresent(income -> {
            Notification n = new Notification();
            n.setMessage("Income deleted: ₹" + income.getAmount() + " from " + income.getSource());
            n.setTitle("income");
            n.setIsRead(false);
            n.setCreatedAt(LocalDateTime.now());
            notificationRepo.save(n);
        });

        repo.deleteById(id);
    }
}