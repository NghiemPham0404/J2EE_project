package com.example.J2EE_project.services;

import com.example.J2EE_project.exceptions.InvalidPageException;
import com.example.J2EE_project.exceptions.NotFoundException;
import com.example.J2EE_project.models.Post;
import com.example.J2EE_project.models.PostView;
import com.example.J2EE_project.repositories.PostRepository;
import com.example.J2EE_project.repositories.PostViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostViewRepository postViewRepository;

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
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 20);
        Page<Post> postPage = postRepository.findAllByOwnerId(pageable, id);
        if(page+1 > postPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return postPage;

    }

    /**
     * TODO : Hiển thị tất cả post theo trang
     * */
    public Page<Post> listAllNewest(int page) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 20);
        Page<Post> postPage = postRepository.findAll(pageable);
        if(page+1 > postPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return postPage;
    }

    /**
     * TODO : Hiển thị tất cả post theo trang
     * */
    public Page<Post> listAllForViewer(int page) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 20);
        Page<Post> postPage = postRepository.findAllForViewer(pageable);
        if(page+1 > postPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return postPage;
    }

    /**
     * TODO : Hiển thị tất cả post chưa duyệt theo trang
     * */
    public Page<Post> listAllNotApprovedYet(int page) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 20);
        Page<Post> postPage = postRepository.findAllNotApprovedYet(pageable);
        if(page+1 > postPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return postPage;
    }


    /**
     * TODO : tìm kiếm tất cả post (do chính mình viết) theo trang
     * */
    public Page<Post> search(int page, String query,  int id) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 20);
        Page<Post> postPage = postRepository.findAllByTitle(pageable, query, id);
        if(page+1 > postPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return postPage;
    }

    /**
     * TODO : tìm kiếm tất cả post theo trang
     * */
    public Page<Post> search(int page, String query) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 20);
        Page<Post> postPage = postRepository.findAllByTitle(pageable, query);
        if(page+1 > postPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return postPage;
    }

    /**
     * TODO : tìm kiếm tất cả post theo trang
     * */
    public Page<Post> searchForUser(int page, String query) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 20);
        Page<Post> postPage = postRepository.findAllByTitleForViewer(pageable, query);
        if(page+1 > postPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return postPage;
    }

    /**
     * TODO : lấy một post
     * */
    public Post get(String id) {
        return postRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException(NotFoundException.NOT_FOUND));
    }

     /**
     * TODO : lấy một post
     * */
    public Post getForUser(String id) {
        return postRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException(NotFoundException.NOT_FOUND));
    }

    /**
     * TODO : Duyệt một post
     * */
    public String approvePost(String id){
        get(id);
        postRepository.approvePost(UUID.fromString(id));
        return "post approved successfully";
    }

    /**
     * TODO : Thêm một lượt xem của một post
     * */
    public void addOneViewIntoPost(String id){
        Post post = get(id);
        PostView postView = new PostView();
        postView.setPost(post);
        postView.setTime(new Date(System.currentTimeMillis()));
        postViewRepository.save(postView);
    }
}
