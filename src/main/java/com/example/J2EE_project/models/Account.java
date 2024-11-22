package com.example.J2EE_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birthDate;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private boolean active;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "account")
    @JsonBackReference
    private List<Post> posts;
}

