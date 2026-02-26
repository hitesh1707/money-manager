package com.example.moneymanager.service;

import com.example.moneymanager.model.Notification;
import com.example.moneymanager.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    // create notification
    public Notification create(Notification notification) {
        return repo.save(notification);
    }

    // get all
    public List<Notification> getAll() {
        return repo.findAll();
    }

    // get unread
    public List<Notification> getUnread() {
        return repo.findByIsReadFalse();
    }

    // unread count
    public long getUnreadCount() {
        return repo.countByIsReadFalse();
    }

    // mark as read
    public Notification markAsRead(Long id) {
        Notification n = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        n.setRead(true);
        return repo.save(n);
    }

    // delete
    public void delete(Long id) {
        repo.deleteById(id);
    }
}