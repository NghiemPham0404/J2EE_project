package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.models.CharityEvent;
import com.example.J2EE_project.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, CrudRepository<Account, Integer> {
    /**
     *
     * TODO : Tìm tất cả các tài khoản để quản lý
     *
     */
    @Query("Select a from Account a where a.role.id != 1 and a.id != :id")
    Page<Account> listAllManagableAccount(int id, Pageable pageable);

    Page<Account> findByRole(Role role, Pageable pageable);
}
