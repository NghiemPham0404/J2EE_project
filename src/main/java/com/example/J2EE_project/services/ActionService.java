package com.example.J2EE_project.services;

import com.example.J2EE_project.models.Action;
import com.example.J2EE_project.repositories.ActionRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public class ActionService implements serviceInterface<Action, Integer>{
    ActionRepository actionRepository;
    @Override
    public String create(Action action) {
        actionRepository.save(action);
        return "Action created";
    }

    @Override
    public String update(Action action) {
        actionRepository.save(action);
        return "Action updated";
    }

    @Override
    public String delete(Integer integer) {
        actionRepository.deleteById(integer);
        return "Action deleted";
    }

    @Override
    public Action get(Integer integer) {
        return actionRepository.findById(integer).get();
    }

    @Override
    public List<Action> listAll() {
        return actionRepository.findAll();
    }

    @Override
    public List<Action> listAllNewest() {
        return List.of();
    }

    @Override
    public Page<Action> listAll(int page) {
        return null;
    }

    @Override
    public Page<Action> listAllNewest(int page) {
        return null;
    }
}
