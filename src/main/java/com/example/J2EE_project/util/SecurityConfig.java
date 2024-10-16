package com.example.J2EE_project.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //$2a$10$EMDYO.kYvP4xJHkJqkeCzOmqTNC7WFR7vskYdpOym5NFvtIdxGkYe
    //$2a$10$JOS.U7DKSQgWj9nC5ey5lOgYTo.EVOHcfIwK3A1/L9lb179BvlP0C
    public static void main(String[] args) {
        SecurityConfig securityConfig = new SecurityConfig();
        String rawPassword = "admin";
//        String encodedPassword = securityConfig.passwordEncoder().encode(rawPassword);
//        System.out.println("Encoded password: " + encodedPassword);
        boolean valid = securityConfig.passwordEncoder().matches(rawPassword, "$2a$10$JOS.U7DKSQgWj9nC5ey5lOgYTo.EVOHcfIwK3Aasdfsa9lb179BvlP0C");
        System.out.println(valid);
    }
}
