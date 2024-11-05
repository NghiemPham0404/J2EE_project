package com.example.J2EE_project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CharityEvent {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(Types.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Lob
    private String description;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column
    private BigDecimal goalAmount;

    @Column
    private boolean isDisbursed;

    @OneToMany(mappedBy = "charityEvent")
    List<Post> posts;

    @OneToMany(mappedBy = "charityEvent")
    List<TransferSession> transferSessions;

    public BigDecimal getCurrentAmount() {
        BigDecimal currentAmount = new BigDecimal(0);
        for(TransferSession session : transferSessions) {
            currentAmount = currentAmount.add(session.getAmount());
        }
        return currentAmount;
    }
}

