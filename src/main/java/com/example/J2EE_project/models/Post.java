package com.example.J2EE_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.web.bind.annotation.PostMapping;

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

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date timePost;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date approved;

    @Column
    private String thumbImg;

    @ManyToOne
    @JoinColumn(name = "ce_id")
    private CharityEvent charityEvent;

    @ManyToOne
    @JoinColumn(name = "ac_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference("post-postViews")
    @JsonIgnore
    private List<PostView> postViews;

    @Transient
    private String author;

    @Transient
    private long viewed;

    @PostLoad
    private void updateAuthorAndView() {
        System.out.print("parse" + account.getName());
        author = account.getName();
        viewed = postViews.size();
    }
}

