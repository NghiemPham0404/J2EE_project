package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.CharityEvent;
import com.example.J2EE_project.models.TransferSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.J2EE_project.DTOs.BalanceTrackingDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface BalanceTrackingRepository extends JpaRepository<TransferSession, UUID>{
    /**
     * TODO : theo dõi tài khoản theo năm
     */
    @Query(value = """
        SELECT 
            month_m,
            SUM(transfer_total) AS transferTotal,
            SUM(charity_event_total) AS charityEventDisburse
        FROM (
            SELECT 
                MONTH(ts.time) AS month_m,
                SUM(ts.amount) AS transfer_total,
                0 AS charity_event_total
            FROM 
                transfer_session ts
            WHERE 
                YEAR(ts.time) = :year
            GROUP BY 
                MONTH(ts.time)
            UNION
            SELECT 
                MONTH(ce.is_disbursed) AS month_m,
                0 AS transfer_total,
                SUM(ts.amount) AS charity_event_total
            FROM 
                charity_event ce
            JOIN 
                transfer_session ts ON ce.id = ts.ce_id
            WHERE 
                ce.is_disbursed <= CURRENT_TIMESTAMP 
                AND YEAR(ce.is_disbursed) = :year
            GROUP BY 
                MONTH(ce.is_disbursed)
        ) AS combined
        GROUP BY 
            month_m
        ORDER BY 
            month_m
        """, nativeQuery = true)
    List<Object[]> getBalanceTrackingOfYear(int year);


    @Query(value="""
            SELECT distinct YEAR(ce.start_time)
            FROM charity_event AS ce
            ORDER BY YEAR(ce.start_time) DESC
            """, nativeQuery = true)
    List<Integer> getAllActiveYear();

     @Query(value="""
             SELECT SUM(total) as remain
             FROM
             ((SELECT SUM(ts.amount) total
             FROM transfer_session ts)
             UNION
             (SELECT -SUM(ts.amount) total
             FROM charity_event as ce JOIN transfer_session AS ts ON ce.id = ts.ce_id
             WHERE
             ce.is_disbursed <= CURRENT_TIMESTAMP
             ))
             AS combined """,nativeQuery = true)
    List<BigDecimal> getRemainBalance();
}
