package com.example.J2EE_project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * TODO : Thống kê các bài viết được xem nhiều nhất
 * */
@Data
@AllArgsConstructor
public class MostViewedPostsDTO {
    private UUID postId;
    private String title;
    private Long count;
}
