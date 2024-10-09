package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Integer> {
}
