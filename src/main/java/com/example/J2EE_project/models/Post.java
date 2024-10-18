package com.example.J2EE_project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "ce_id")
    private CharityEvent charityEvent;

    @ManyToOne
    @JoinColumn(name = "ac_id")
    private Account account;

    @OneToMany(mappedBy = "post")
    private List<PostView> postViews;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}

