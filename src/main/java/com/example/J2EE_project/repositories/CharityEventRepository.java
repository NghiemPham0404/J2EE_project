package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.CharityEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CharityEventRepository extends CrudRepository<CharityEvent, UUID>{
    /**
     *
     * TODO : tìm các sự kiện từ thiện mà chưa có bài viết nào
     *
     */
    @Query("Select ce from CharityEvent ce where ce.posts is empty")
    Page<CharityEvent> findAllCharityEventWithoutPost(Pageable pageable);
}
