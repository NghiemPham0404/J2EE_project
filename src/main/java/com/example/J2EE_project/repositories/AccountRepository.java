package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Integer>, CrudRepository<Account, Integer> {

}
