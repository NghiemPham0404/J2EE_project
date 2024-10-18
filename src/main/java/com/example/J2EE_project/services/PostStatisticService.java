package com.example.J2EE_project.services;

import com.example.J2EE_project.DTOs.MostPostsAccountsDTO;
import com.example.J2EE_project.DTOs.MostViewedPostsDTO;
import com.example.J2EE_project.repositories.PostStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostStatisticService {
    @Autowired
    PostStatisticRepository accountPostCountRepository;

    public List<MostPostsAccountsDTO> statisticMostPostAccounts(Date startTime, Date endTime){
        return accountPostCountRepository.statisticAccountsWithMostPosts(startTime,endTime);
    }

    public List<MostViewedPostsDTO> statisticMostViewedPosts(Date startTime, Date endTime){
        return accountPostCountRepository.statisticPostViews(startTime, endTime);
    }
}
