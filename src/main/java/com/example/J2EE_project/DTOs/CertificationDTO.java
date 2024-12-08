package com.example.J2EE_project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDTO {
    private String fullName;
    private String event;
    private String time;
    private String donation;
}