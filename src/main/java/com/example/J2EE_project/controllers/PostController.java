package com.example.J2EE_project.controllers;

import com.example.J2EE_project.models.Post;
import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Create a new post
    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody Post post) {
        String response = postService.create(post);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    // Update an existing post
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable String id, @RequestBody Post post) {
        postService.get(id);
        String response = postService.update(post);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    // Delete a post by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable String id) {
        String response = postService.delete(id);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    // Get a specific post by ID
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        return postService.get(id);
    }

    // List all posts (paginated)
    @GetMapping
    public ResponseEntity<Object> listAllPosts(@RequestParam(defaultValue = "0") int page) {
        Page<Post> posts = postService.listAllNewest(page);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    // List all posts by the user (paginated)
    @GetMapping("/my-posts")
    public ResponseEntity<Object> listAllMyPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam int ownerId) {
        Page<Post> posts = postService.listAllNewest(page, ownerId);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    // Search posts by title (paginated, all posts)
    @GetMapping("/search")
    public ResponseEntity<Object> searchPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam String query) {
        Page<Post> posts = postService.search(page, query);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }

    // Search posts by title written by a specific user (paginated)
    @GetMapping("/search/my-posts")
    public ResponseEntity<Object> searchMyPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam String query,
            @RequestParam int ownerId) {
        Page<Post> posts = postService.search(page, query, ownerId);
        return ResponseBuilder.buildResponse(posts, HttpStatus.OK);
    }
}

