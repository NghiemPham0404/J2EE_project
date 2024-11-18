package com.example.J2EE_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class J2EeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(J2EeProjectApplication.class, args);
    }

}
