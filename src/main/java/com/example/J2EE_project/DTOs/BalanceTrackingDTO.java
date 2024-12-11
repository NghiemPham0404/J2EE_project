package com.example.J2EE_project.DTOs;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceTrackingDTO {
     int month;
     BigDecimal transferTotal;
     BigDecimal charityEventDisburse;
}
