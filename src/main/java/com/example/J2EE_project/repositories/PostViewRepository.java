package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.PostView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostViewRepository extends JpaRepository<PostView, Integer> {
}
