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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/personal-info/**").permitAll()
                                .requestMatchers("/api/email/**").permitAll()
                                .requestMatchers("/api/posts","/api/posts/search","api/posts/approved/**","/api/posts/post/*/viewed").permitAll()
                                .requestMatchers("/api/charity-events/transfer").permitAll()
                                .requestMatchers("/api/charity-events/*/all-transfer").permitAll()
                                .requestMatchers("/api/statistic/charity-event/*/top-10").permitAll()
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

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:8081") // Frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}
