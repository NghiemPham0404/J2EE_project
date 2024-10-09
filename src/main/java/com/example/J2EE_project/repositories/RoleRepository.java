package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
