package com.example.J2EE_project.controllers;

import com.example.J2EE_project.DTOs.CertificationDTO;
import com.example.J2EE_project.services.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/certification")
    @Operation(summary = "Gửi minh chứng đến email người dùng")
    public ResponseEntity<Object> sendEmail(@RequestParam(value = "recipientAddress") String recipientAddress, @RequestParam(value = "transferId") String transferId) {
        emailService.sendDonationCertification(recipientAddress, transferId);
        return new ResponseEntity<>("gửi minh chứng thành công", HttpStatus.OK);
    }
}
