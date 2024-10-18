package com.example.J2EE_project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
/**
 * TODO : thống kế những charity event có nhiều ủng hộ nhất
 * */
@Data
@AllArgsConstructor
public class MostDonationEventsDTO {
    private UUID charityEventId;
    private String name;
    private BigDecimal totalDonationAmount;
    private BigDecimal goalAmount;
}
