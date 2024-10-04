package com.example.J2EE_project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface serviceInterface <T, ID>{

    String create(T t);
    String update(T t);
    String delete(ID id);
    List<T> listAll();
    List<T> listAllNewest();
    Page<T> listAll(int page);
    Page<T> listAllNewest(int page);
    T get(ID id);
}
