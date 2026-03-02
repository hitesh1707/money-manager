package com.example.moneymanager.service;

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

        String verificationUrl =
                backendUrl + "/api/v1.0/auth/verify?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Verify Your Email - Money Manager");
        message.setText("Click the link to activate your account:\n\n" + verificationUrl);

        System.out.println("Sending verification email to: " + toEmail);
        System.out.println("Verification link: " + verificationUrl);
        mailSender.send(message);
    }
}