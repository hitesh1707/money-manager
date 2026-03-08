package com.example.moneymanager.controller;

import com.example.moneymanager.model.Notification;
import com.example.moneymanager.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    // create notification
    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return service.create(notification);
    }

    // get all notifications
    @GetMapping
    public List<Notification> getAll() {
        return service.getAll();
    }

    // get unread notifications
    @GetMapping("/unread")
    public List<Notification> getUnread() {
        return service.getUnread();
    }

    // unread count
    @GetMapping("/unread/count")
    public long unreadCount() {
        return service.getUnreadCount();
    }

    // mark as read
    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {
        return service.markAsRead(id);
    }

    // delete notification
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}