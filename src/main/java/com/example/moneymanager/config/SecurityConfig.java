package com.example.moneymanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // disable CSRF (needed for Postman)
                .csrf(csrf -> csrf.disable())

                // disable default login page
                .formLogin(form -> form.disable())

                // disable HTTP basic auth popup
                .httpBasic(httpBasic -> httpBasic.disable())

                // allow auth endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1.0/auth/**").permitAll()
                        .requestMatchers("/api/v1.0/dashboard/**").authenticated()
                        .requestMatchers("/api/v1.0/notifications/**").authenticated()
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}