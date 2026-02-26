package com.example.moneymanager.service;

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
    private UserRepository repo;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER USER
    @Autowired
    private BCryptPasswordEncoder encoder;

    public String register(User user) {

        if (repo.existsByEmail(user.getEmail()))
            return "Email already exists";

        user.setPassword(encoder.encode(user.getPassword())); // IMPORTANT

        String token = UUID.randomUUID().toString();
        user.setActivationToken(token);

        repo.save(user);

        return "Registered";
    }

    // ACTIVATE ACCOUNT
    public String activateAccount(String token) {

        User user = repo.findByActivationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        user.setActive(true);
        user.setActivationToken(null);

        repo.save(user);

        return "Account activated successfully";
    }

    public String login(String email, String password) {

        Optional<User> optionalUser = repo.findByEmail(email);

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