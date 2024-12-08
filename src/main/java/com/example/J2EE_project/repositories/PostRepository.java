package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID>, PagingAndSortingRepository<Post, UUID> {
    /**
     * TODO : Tìm các bài viết do chính mình viết
     * */
    @Query("SELECT a FROM Post a WHERE a.account.id = :id")
    Page<Post> findAllByOwnerId(Pageable pageable, int id);

     /**
     * TODO : Tìm các bài viết cho người xem
     * */
    @Query("SELECT a FROM Post a WHERE a.approved <= CURRENT_TIMESTAMP")
    Page<Post> findAllForViewer(Pageable pageable);

    /**
     * TODO :  Tìm các bài viết bằng title (cho admin)
     * */
    @Query("SELECT a FROM Post a WHERE a.title like %:title%")
    Page<Post> findAllByTitle(Pageable pageable,String title);

    /**
     * TODO :  Tìm các bài viết bằng title (cho người xem)
     * */
    @Query("SELECT a FROM Post a WHERE a.title like %:title% and a.approved <= CURRENT_TIMESTAMP")
    Page<Post> findAllByTitleForViewer(Pageable pageable,String title);

    /**
     * TODO : Tìm các bài viết bằng title do chính mình viết
     * */
    @Query("SELECT a FROM Post a WHERE a.account.id = :id AND a.title like %:title%")
    Page<Post> findAllByTitle(Pageable pageable, String title, int id);

    /**
      * TODO : duyệt một post
      * */
     @Modifying
     @Query("UPDATE Post ce SET ce.approved = CURRENT_TIMESTAMP WHERE ce.id = :id")
     void approvePost(UUID id);
}
