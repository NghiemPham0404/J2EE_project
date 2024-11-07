package com.example.J2EE_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(Types.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Lob
    private String body;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date timePost;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date approved;

    @Column
    private String thumbImg;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ce_id")
    private CharityEvent charityEvent;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "ac_id")
    private Account account;

    @OneToMany(mappedBy = "post")
    @JsonBackReference
    private List<PostView> postViews;
}

