package com.example.J2EE_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RoleAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "_create")
    private boolean create;

    @Column(name = "_update")
    private boolean update;
    @Column(name = "_delete")
    private boolean delete;

    @Column(name = "_read")
    private boolean read;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;
}
