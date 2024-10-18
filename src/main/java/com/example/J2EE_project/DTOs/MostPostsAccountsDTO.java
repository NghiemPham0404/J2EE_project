package com.example.J2EE_project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * TODO : DTO thống kế số lượng bài viết của từng tài khoản
 * */
@Data
@AllArgsConstructor
public class MostPostsAccountsDTO {
    int accountId;
    String accountName;
    Long postCount;
}
