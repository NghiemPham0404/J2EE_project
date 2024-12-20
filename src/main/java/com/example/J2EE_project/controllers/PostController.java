package com.example.J2EE_project.controllers;

import com.example.J2EE_project.models.Post;
import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * TODO : Tạo một Post mới
     */
    @PreAuthorize("hasAuthority('Post Management create')")
    @Operation(summary = "Tạo một Post mới")
    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody Post post) {
        String response = postService.create(post);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    /**
     * TODO : Sửa một Post
     */
    @PreAuthorize("hasAuthority('Post Management update')")
    @Operation(summary = "Sửa một Post")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable String id, @RequestBody Post post) {
        postService.get(id);
        String response = postService.update(post);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    /**
     * TODO : Xóa một Post bằng id
     */
    @PreAuthorize("hasAuthority('Post Management delete')")
    @Operation(summary = "Xóa một Post bằng id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable String id) {
        String response = postService.delete(id);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    /**
     * TODO : Lấy một Post bằng id
     */
    @PreAuthorize("hasAuthority('Post Management read')")
    @GetMapping("/{id}")
    @Operation(summary = "Lấy một post bằng id")
    public Post getPostById(@PathVariable String id) {
        return postService.get(id);
    }

    /**
     * TODO : Lấy một Post bằng id cho người xem
     */
    @GetMapping("/approved/{id}")
    @Operation(summary = "Lấy một post bằng id cho người xem")
    public Post getPostByIdForUser(@PathVariable String id) {
        return postService.getForUser(id);
    }

    /**
     * TODO : Lấy danh sách tất cả các post
     */
    @PreAuthorize("hasAuthority('Post Management read')")
    @Operation(summary = "Lấy danh sách tất cả các post")
    @GetMapping("all")
    public ResponseEntity<Object> listAllPosts(@RequestParam(defaultValue = "0") int page) {
        Page<Post> posts = postService.listAllNewest(page);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    /**
     * TODO : Lấy danh sách tất cả các post đã được duyệt (cho người xem)
     */
    @GetMapping
    @Operation(summary = "Lấy danh sách tất cả các post đã được duyệt cho người xem")
    public ResponseEntity<Object> listAllPostsForUser(@RequestParam(defaultValue = "0") int page) {
        Page<Post> posts = postService.listAllForViewer(page);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    /**
     * TODO : Lấy danh sách tất cả các post chưa duyệt
     */
    @GetMapping("/not-approved")
    @Operation(summary = "Lấy danh sách tất cả các post chưa duyệt")
    public ResponseEntity<Object> listAllPostsNotApprovedYet(@RequestParam(defaultValue = "0") int page) {
        Page<Post> posts = postService.listAllNotApprovedYet(page);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    /**
     * TODO : Lấy danh sách tất cả các post do chính mình viết
     */
    @PreAuthorize("hasAuthority('Post Management read')")
    @Operation(summary = "Lấy danh sách tất cả các post do chính mình viết")
    @GetMapping("/my-posts")
    public ResponseEntity<Object> listAllMyPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam int ownerId) {
        Page<Post> posts = postService.listAllNewest(page, ownerId);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    /**
     * TODO : Tìm kiếm các post theo tên
     */
    @PreAuthorize("hasAuthority('Post Management read')")
    @Operation(summary = "Tìm kiếm các post theo tên")
    @GetMapping("/search-all")
    public ResponseEntity<Object> searchPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam String query) {
        Page<Post> posts = postService.search(page, query);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    /**
     * TODO : Tìm kiếm tất cả các post đã được duyệt (cho người xem)
     */
    @Operation(summary = "Tìm kiếm các post theo tên và đã được duyệt (cho người xem)")
    @GetMapping("/search")
    public ResponseEntity<Object> searchPostsForUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam String query) {
        Page<Post> posts = postService.searchForUser(page, query);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    /**
     * TODO : tìm kiếm tất cả các post do chính mình viết
     */
    @PreAuthorize("hasAuthority('Post Management read')")
    @GetMapping("/search/my-posts")
    @Operation(summary = "Tìm kiếm tất cả các post do chính mình viết")
    public ResponseEntity<Object> searchMyPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam String query,
            @RequestParam int ownerId) {
        Page<Post> posts = postService.search(page, query, ownerId);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    /**
     * TODO : phê duyệt một bài post
     */
    @PreAuthorize("hasAuthority('Post Management update')")
    @Operation(summary = "Phê duyệt một bài post")
    @PostMapping("/post/{id}/approved")
    public ResponseEntity<Object> approvePost(@PathVariable String id) {
        return ResponseBuilder.buildResponse(postService.approvePost(id), HttpStatus.OK);
    }

    /**
     * TODO : Thêm một lượt xem của một post
     */
    @Operation(summary = "Thêm một lượt xem của một post")
    @PostMapping("/post/{id}/viewed")
    public void viewPost(@PathVariable String id) {
        postService.addOneViewIntoPost(id);
    }


}

