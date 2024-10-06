package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.models.CharityEvent;
import com.example.J2EE_project.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, CrudRepository<Account, Integer> {
    /**
     * Tìm tất cả các tài khoản để quản lý
     */
    @Query("Select a from Account a where a.role.id != 1 and a.id != :id")
    Page<Account> listAllAccount(int id, Pageable pageable);

    /**
     *Tìm tài khoản theo tên
     */
     Page<Account> findByNameIgnoreCase(String name, Pageable pageable);

    /**
     *Tìm tài khoản role
     */
     @Query("Select a from Account a where a.role.id =:role_id")
     Page<Account> findByRole(int role_id, Pageable pageable);

     /**
     *Khóa/Mở khóa tài khoản
     */
     @Modifying
     @Query("update Account u set u.active = :active  where u.id = :id")
     void updateActiveStatus (int id, boolean active);
}
