package com.example.J2EE_project.controllers;

import com.example.J2EE_project.DTOs.AccountDTO;
import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.PersonalInfoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;

    // Get personal information by account ID
    @GetMapping("/{id}")
    @Operation(summary = "Lấy thông tin người dùng bằng id")
    public AccountDTO getPersonalInfo(@PathVariable int id) {
        return personalInfoService.getPersonalInfo(id);
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
    public AccountDTO login(
            @RequestParam String username,
            @RequestParam String password) {
        return personalInfoService.login(username, password);
    }
}
