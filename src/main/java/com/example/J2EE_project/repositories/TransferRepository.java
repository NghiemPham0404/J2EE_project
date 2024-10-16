package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.TransferSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<TransferSession, UUID>, PagingAndSortingRepository<TransferSession, UUID> {
    @Query("SELECT a FROM TransferSession a WHERE a.charityEvent.id = :ceId")
    List<TransferSession> findAllByCharityEvent(UUID ceId);
}
