package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID>, PagingAndSortingRepository<Post, UUID> {

}
