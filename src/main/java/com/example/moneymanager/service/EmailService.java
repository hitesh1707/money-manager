package com.example.moneymanager.service;

import com.example.moneymanager.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailService {

    @Value("${email.enabled:false}")
    private boolean emailEnabled;

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String email, String token) {

        if (!emailEnabled) {
            System.out.println("Email disabled. Verification token: " + token);
            return;
        }

        try {
            String link = "http://localhost:5173/api/v1.0/auth/verify?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Verify your account");
            message.setText("Click to verify: " + link);

            mailSender.send(message);

        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }
    }
}