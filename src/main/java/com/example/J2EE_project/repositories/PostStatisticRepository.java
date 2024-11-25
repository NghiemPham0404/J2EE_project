package com.example.J2EE_project.repositories;

import com.example.J2EE_project.DTOs.MostPostsAccountsDTO;
import com.example.J2EE_project.DTOs.MostViewedPostsDTO;
import com.example.J2EE_project.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostStatisticRepository extends JpaRepository<Account, Integer> {
    /**
     * TODO : Thống kế số lượng bài viết của từng tài khoản
     */
    @Query("SELECT new com.example.J2EE_project.DTOs.MostPostsAccountsDTO(a.id, a.name, COUNT(p)) " +
            "FROM Post p Right JOIN p.account a " +
            "WHERE :startDate <= p.timePost OR p.timePost <= :endDate " +
            "GROUP BY a.id, a.name " +
            "ORDER BY COUNT(p) DESC")
    List<MostPostsAccountsDTO> statisticAccountsWithMostPosts(Date startDate, Date endDate);

    /**
     * TODO : Thống kê lượt xem của các Post
     */
    @Query("SELECT new com.example.J2EE_project.DTOs.MostViewedPostsDTO(p.id, p.title, COUNT(p)) " +
            "FROM Post p LEFT JOIN PostView pv " +
            "WHERE :startDate <= pv.time OR pv.time <= :endDate " +
            "GROUP BY p.id, p.title " +
            "ORDER BY COUNT(p) DESC")
    List<MostViewedPostsDTO> statisticPostViews(Date startDate, Date endDate);
}
