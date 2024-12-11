package com.example.J2EE_project.controllers;

import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.BalanceTrackingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class BalanceTrackingController{
    @Autowired
    private BalanceTrackingService balanceTrackingService;

    @Operation(summary = "Lấy danh sách số tiền nhận được, số tiền giải ngân trong năm")
    @GetMapping("/api/balance-tracking")
    public ResponseEntity<Object> getBalanceTrackingOfYear(@RequestParam("year") int year){
        return ResponseBuilder.buildResponse(balanceTrackingService.getBalanceTrackingOfYear(year), HttpStatus.OK);
    }

    @Operation(summary = "Lấy danh sách những năm hoạt động nhận gây quỹ")
    @GetMapping("/api/all-active-year")
    public List<Integer> getAllActiveYear(){
        return balanceTrackingService.getAllActiveYear();
    }

    @Operation(summary = "Lấy tổng số tiền còn lại trong quỹ")
    @GetMapping("/api/remain-balance")
    public BigDecimal getRemainBalance(){
        return balanceTrackingService.getRemainBalance();
    }
}
