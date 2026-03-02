package com.example.moneymanager.service;

import com.example.moneymanager.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${money.manager.backend.url}")
    private String backendUrl;

    public void sendVerificationEmail(String toEmail, String token) {

        try {
            String verificationUrl = backendUrl+"/api/v1.0/auth/verify?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(fromEmail);
            message.setTo(toEmail);
            message.setSubject("Verify your account");
            message.setText("Click to verify: " + verificationUrl);

            mailSender.send(message);

            System.out.println("Email sent successfully");

        } catch (Exception e) {
            System.out.println("Email sending failed inside EmailService: " + e.getMessage());
        }
    }


}