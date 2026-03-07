package com.example.moneymanager.controller;

import com.example.moneymanager.dto.LoginRequest;
import com.example.moneymanager.dto.RegisterRequest;
import com.example.moneymanager.model.User;
import com.example.moneymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService service;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            String response = service.register(request);
            return ResponseEntity.ok(Map.of("message", response));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
//    @GetMapping("/verify")
//    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
//
//        String response = service.activateAccount(token);
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = service.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }


    @PostMapping("/upload-profile")
    public ResponseEntity<?> uploadProfile(
            @RequestParam("file") MultipartFile file) {

        String imageUrl = service.upload(file);

        return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
    }
}