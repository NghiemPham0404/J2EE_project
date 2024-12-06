package com.example.J2EE_project.repositories;

import com.example.J2EE_project.DTOs.MostCharitablePeopleDTO;
import com.example.J2EE_project.DTOs.MostDonationEventsDTO;
import com.example.J2EE_project.models.CharityEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventStatisticRepository extends JpaRepository<CharityEvent, UUID> {

     /**
     * TODO : Thống kế Charity Event có nhiều ủng hộ nhất
     */
    @Query("SELECT new com.example.J2EE_project.DTOs.MostDonationEventsDTO(ce.id, ce.name, SUM(ts.amount), ce.goalAmount) " +
            "FROM CharityEvent ce LEFT JOIN ce.transferSessions ts " +
            "WHERE :startDate <= ce.startTime OR ce.endTime <= :endDate " +
            "GROUP BY ce.id, ce.name, ce.goalAmount " +
            "ORDER BY SUM(ts.amount) DESC")
    List<MostDonationEventsDTO> statisticMostDonationEvents(Date startDate, Date endDate);

    /**
     * TODO : Thống kế những người ủng hộ nhiều nhất trong 1 Charity Event
     */
    @Query("SELECT new com.example.J2EE_project.DTOs.MostCharitablePeopleDTO(ts.id, ts.name, ts.amount) " +
            "FROM CharityEvent ce LEFT JOIN ce.transferSessions ts " +
            "WHERE (:startDate <= ce.startTime OR ce.endTime <= :endDate) AND ce.id = :ce_id " +
            "GROUP BY ts.id " +
            "ORDER BY ts.amount DESC")
    List<MostCharitablePeopleDTO> statisticMostCharitablePeople(UUID ce_id, Date startDate, Date endDate);


     @Query("SELECT ce FROM CharityEvent ce " +
       "where ce.isDisbursed =true " +
       "AND (ce.startTime >= :startDate AND ce.endTime <= :endDate) ")
     List<CharityEvent> getCharityEventBeDisbursedIn(Date startDate, Date endDate);
}
