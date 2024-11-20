package com.example.J2EE_project.services;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.models.RoleAction;
import com.example.J2EE_project.repositories.AccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtTokenService {

    @Value("${SECRET_KEY}")
    String SECRET_KEY;

    @Autowired
    private AccountRepository accountRepository;

    public String generateToken(String userName) {
        Account account = accountRepository.findByUsername(userName);
        List<RoleAction> roleActions = account.getRole().getRoleActions();

        List<String> authorities = account.getRole().getRoleActions().stream()
                .flatMap(roleAction -> Stream.of(
                        roleAction.isCreate() ? roleAction.getAction().getName() + " create" : null,
                        roleAction.isUpdate() ? roleAction.getAction().getName() + " update" : null,
                        roleAction.isDelete() ? roleAction.getAction().getName() + " delete" : null,
                        roleAction.isRead() ? roleAction.getAction().getName() + " read" : null
                ))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        for (String authority : authorities) {
            System.out.println(authority);
        }

        return createToken(authorities, userName);
    }

    // Generate JWT Token
    public String createToken(List authorities, String username) {
        return Jwts.builder()
                .claim("authorities", authorities)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))  // 1 day expiry
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Get username from JWT token
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        System.out.println("getAuthoritiesFromToken: " + token);
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        List<GrantedAuthority> authorities = (((List<?>) claims.get("authorities")).stream()
                .map(auth -> new SimpleGrantedAuthority((String) auth))
                .collect(Collectors.toList()));
        for (GrantedAuthority grantedAuthority : authorities) {
            System.out.println(grantedAuthority);
        }
        return authorities;
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // Remove "Bearer " prefix
        }
        return null;
    }
}

