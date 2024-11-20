package com.example.J2EE_project.util;

import com.example.J2EE_project.exceptions.CustomAccessDeniedHandler;
import com.example.J2EE_project.filters.JwtAuthenticationFilter;
import com.example.J2EE_project.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http, JwtTokenService jwtTokenProvider,CustomAuthenEntryPoint authenticationEntryPoint, CustomAccessDeniedHandler accessDeniedHandler) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider);

        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/home/**").permitAll()
                                .requestMatchers("/api/personal-info/**").permitAll()
                                .anyRequest().authenticated()

                )
                .exceptionHandling(exceptions -> exceptions// Handle 401 Unauthorized
                        .accessDeniedHandler(accessDeniedHandler) // Handle 403 Forbidden
                        .authenticationEntryPoint(authenticationEntryPoint)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // No session used, JWT-based authentication
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Add the JWT filter

        return http.build();
    }


    public static void main(String[] args) {
        SecurityConfig securityConfig = new SecurityConfig();
        String rawPassword = "123123";
        String encodedPassword = securityConfig.passwordEncoder().encode(rawPassword);
        System.out.println("Encoded password: " + encodedPassword);
//        boolean valid = securityConfig.passwordEncoder().matches(rawPassword, "$2a$10$JOS.U7DKSQgWj9nC5ey5lOgYTo.EVOHcfIwK3Aasdfsa9lb179BvlP0C");
//        System.out.println(valid);
    }
}
