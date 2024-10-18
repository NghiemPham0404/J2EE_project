package com.example.J2EE_project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * TODO : Thống kê ai ủng hộ nhiều nhất trong một Charity Event
 * */
@Data
@AllArgsConstructor
public class MostCharitablePeopleDTO {
    private UUID ts_id;
    private String name;
    private BigDecimal amount;
}
