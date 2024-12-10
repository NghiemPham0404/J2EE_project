package com.example.J2EE_project.controllers;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.DTOs.JwtDto;
import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.AccountService;
import com.example.J2EE_project.services.PersonalInfoService;
import com.example.J2EE_project.services.JwtTokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {
    @Autowired
    private JwtTokenService jwtTokenProvider;

    @Autowired
    private PersonalInfoService personalInfoService;
    @Autowired
    private AccountService accountService;

    // Get personal information by account ID
    @GetMapping("/{id}")
    @Operation(summary = "Lấy thông tin người dùng bằng id")
    public Account getPersonalInfo(@PathVariable int id, HttpServletRequest httpServletRequest) {
        String username = jwtTokenProvider.getUsernameFromToken(jwtTokenProvider.extractTokenFromRequest(httpServletRequest));
        return personalInfoService.getPersonalInfo(id, username);
    }

    // Get personal information by account ID
    @GetMapping("/my-info")
    @Operation(summary = "Lấy thông tin người dùng bằng jwt")
    public Account getPersonalInfo(HttpServletRequest httpServletRequest) {
        String extractedUsername = jwtTokenProvider.getUsernameFromToken(jwtTokenProvider.extractTokenFromRequest(httpServletRequest));
        return personalInfoService.getPersonalInfoByUsername(extractedUsername);
    }

    // Change password for an account by ID
    @PutMapping("/{id}/change-password")
    @Operation(summary = "Thay đổi mật khẩu")
    public ResponseEntity<Object> changePassword(
            @PathVariable int id,
            @RequestParam String newPassword) {
        String response = personalInfoService.changePassword(id, newPassword);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    @GetMapping("/validateEmail")
    @Operation(summary = "xác thực Email")
    public ResponseEntity<Object> getPersonalInfo(@RequestParam(value = "email") String email) {
       return ResponseBuilder.buildResponse(personalInfoService.validateEmail(email)+"", HttpStatus.OK);
    }

    // Change email for an account by ID
    @PutMapping("/{id}/change-email")
    @Operation(summary = "Thay đổi email")
    public ResponseEntity<Object> changeEmail(
            @PathVariable int id,
            @RequestParam String newEmail) {
        String response = personalInfoService.changeEmail(id, newEmail);
        return ResponseEntity.ok(response);
    }

    // Login using username and password
    @PostMapping("/login")
    @Operation(summary = "Đăng nhập")
    public JwtDto login(@RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        System.out.println("Username : "+username +" Password : "+password);
        Account account = personalInfoService.login(username, password);
        String token = jwtTokenProvider.generateToken(account.getUsername());
        Date current = new Date(System.currentTimeMillis() + 864000);
        String expiredAt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(current);
        return new JwtDto(token, expiredAt);
    }

    @GetMapping("/validateAdmin")
    @Operation(summary = "Kiểm tra có phải admin không")
    public boolean
    isAdmin(@RequestParam(value = "username") String username){
           return personalInfoService.isAdmin(username);
    }
}
