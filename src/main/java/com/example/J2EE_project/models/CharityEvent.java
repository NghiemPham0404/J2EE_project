package com.example.J2EE_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
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

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column
    private BigDecimal goalAmount;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date isDisbursed;

    @JsonBackReference("post-charity")
    @OneToMany(mappedBy = "charityEvent")
    List<Post> posts;

    @JsonBackReference
    @OneToMany(mappedBy = "charityEvent")
    List<TransferSession> transferSessions;

    @Transient
    private BigDecimal currentAmount = new BigDecimal(0);

    @PostLoad
    public void updateCurrentAmount() {
        if (transferSessions == null) return;
        for (TransferSession session : transferSessions) {
            currentAmount = currentAmount.add(session.getAmount());
        }
    }
}

