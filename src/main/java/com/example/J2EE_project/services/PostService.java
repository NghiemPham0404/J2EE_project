package com.example.J2EE_project.services;

import com.example.J2EE_project.models.Post;
import com.example.J2EE_project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    /**
     * TODO : Thêm một post
     * */
    public String create(Post post) {
        postRepository.save(post);
        return "post created successfully";
    }

    /**
     * TODO : Sửa một post
     * */
    public String update(Post post) {
        postRepository.save(post);
        return "post updated successfully";
    }

    /**
     * TODO : Xóa một post
     * */
    public String delete(String id) {
        postRepository.deleteById(UUID.fromString(id));
        return "post deleted successfully";
    }

    /**
     * TODO : Hiển thị tất cả post (do chính mình viết) theo trang
     * */
    public Page<Post> listAllNewest(int page, int id) {
        Pageable pageable = PageRequest.of(page, 20);
        return postRepository.findAllByOwnerId(pageable, id);
    }

    /**
     * TODO : Hiển thị tất cả post theo trang
     * */
    public Page<Post> listAllNewest(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return postRepository.findAll(pageable);
    }

    /**
     * TODO : tìm kiếm tất cả post (do chính mình viết) theo trang
     * */
    public Page<Post> search(int page, String query,  int id) {
        Pageable pageable = PageRequest.of(page, 20);
        return postRepository.findAllByTitle(pageable, query, id);
    }

    /**
     * TODO : tìm kiếm tất cả post theo trang
     * */
    public Page<Post> search(int page, String query) {
        Pageable pageable = PageRequest.of(page, 20);
        return postRepository.findAllByTitle(pageable, query);
    }

    /**
     * TODO : lấy một post
     * */
    public Post get(String id) {
        return postRepository.getOne(UUID.fromString(id));
    }
}
