package com.example.J2EE_project.filters;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.models.RoleAction;
import com.example.J2EE_project.services.AccountService;
import com.example.J2EE_project.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    JwtTokenService jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenService jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) {
//            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
//                    "admin"
//                    , null,
//                    jwtTokenProvider.getAllAuthorityForSwaggerUi()
//                    ));
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtTokenProvider.extractTokenFromRequest(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            List<GrantedAuthority> authorities = jwtTokenProvider.getAuthoritiesFromToken(token);

            // Lấy danh sách quyền từ bảng RoleAction

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, authorities));
        }
        filterChain.doFilter(request, response);
    }
}
