package com.example.J2EE_project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @JoinColumn(name = "role_id")
    private Role role;
}

