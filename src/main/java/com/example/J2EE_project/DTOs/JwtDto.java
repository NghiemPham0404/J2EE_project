package com.example.J2EE_project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDto {
    String key;
    String expiredAt;
}
