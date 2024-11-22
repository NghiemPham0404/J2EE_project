package com.example.J2EE_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @ManyToOne
    @JsonBackReference("post-postViews")
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "FK_post_view_post"))
    private Post post;
}


