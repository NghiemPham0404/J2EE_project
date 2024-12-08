package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.CharityEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface CharityEventRepository extends JpaRepository<CharityEvent, UUID>, PagingAndSortingRepository<CharityEvent, UUID> {
    /**
     * TODO : tìm các sự kiện từ thiện mà chưa có bài viết nào
     */
    @Query("Select ce from CharityEvent ce where ce.posts is empty")
    Page<CharityEvent> findAllCharityEventWithoutPost(Pageable pageable);

     /**
     * TODO : tìm các sự kiện từ thiện theo tên
     */
     Page<CharityEvent> findByNameContaining(Pageable pageable, String name);

     /**
     * TODO : tìm các sự kiện từ thiện đang diễn ra
     */
     @Query("SELECT ce FROM CharityEvent ce " +
           "LEFT JOIN ce.transferSessions ts " +
           "WHERE ce.startTime <= CURRENT_TIMESTAMP AND ce.endTime >= CURRENT_TIMESTAMP " +
             "GROUP BY ce " +
             "HAVING SUM(ts.amount) < ce.goalAmount OR SUM(ts.amount) is null")
     Page<CharityEvent> getCharityEventNotEnd(Pageable pageable);

     /**
     * TODO : tìm các sự kiện từ thiện đã kết thúc
     */
     @Query("Select ce from CharityEvent ce where ce.endTime < CURRENT_TIMESTAMP")
     Page<CharityEvent> getCharityEventEnd(Pageable pageable);

     /**
     * TODO : tìm các sự kiện từ thiện đã đạt mục tiêu
     */
     @Query("SELECT ce FROM CharityEvent ce " +
           "LEFT JOIN ce.transferSessions ts " +
           "GROUP BY ce " +
           "HAVING SUM(ts.amount) >= ce.goalAmount")
     Page<CharityEvent> getCharityEventReachGoal(Pageable pageable);


     /**
     * TODO : tìm các sự kiện từ thiện chưa giải ngân dù đã đủ điều kiện
     */
     @Query("SELECT ce FROM CharityEvent ce " +
           "LEFT JOIN ce.transferSessions ts " +
           "GROUP BY ce " +
           "HAVING ce.isDisbursed is null " +
           "AND (ce.endTime < CURRENT_TIMESTAMP OR SUM(ts.amount) >= ce.goalAmount)")
    Page<CharityEvent>getCharityEventNotBeDisbursed(Pageable pageable);

     /**
      * TODO : giải ngân một sự kiện từ thiện
      * */
     @Modifying
     @Transactional
     @Query("update CharityEvent ce set ce.isDisbursed = CURRENT_TIMESTAMP where ce.id = :id")
     void disburseCharityEvent(UUID id);
}
