package com.example.J2EE_project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TransferSession {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(Types.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private BigDecimal amount;

    @Column(length = 280)
    private String description;

    @Column
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "ce_id")
    private CharityEvent charityEvent;
}
