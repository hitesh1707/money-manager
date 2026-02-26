package com.example.moneymanager.service;

import com.example.moneymanager.dto.RegisterRequest;
import com.example.moneymanager.model.User;
import com.example.moneymanager.repository.UserRepository;
import com.example.moneymanager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER USER
    @Autowired
    private BCryptPasswordEncoder encoder;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        String token = UUID.randomUUID().toString();

        User user = new User();
        user.setFullName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setActive(false);
        user.setActivationToken(token);

        userRepository.save(user);

        emailService.sendVerificationEmail(user.getEmail(), token);

        return "Registration successful. Please check your email to verify.";
    }

    // ACTIVATE ACCOUNT
    public String activateAccount(String token) {

        User user = userRepository.findByActivationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        user.setActive(true);
        user.setActivationToken(null);

        userRepository.save(user);

        return "Account activated successfully";
    }

    public String login(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()) {
            return "Invalid email or password";
        }

        User user = optionalUser.get();
        if (!user.getActive())
            throw new RuntimeException("Account not activated");

        if (!encoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid password");

        return jwtUtil.generateToken(email);
    }

    public String upload(MultipartFile file) {

        return null;
    }
}