package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.CharityEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CharityEventRepository extends CrudRepository<CharityEvent, UUID> {

}
