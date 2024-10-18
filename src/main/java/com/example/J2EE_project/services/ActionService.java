package com.example.J2EE_project.services;

import com.example.J2EE_project.models.Action;
import com.example.J2EE_project.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService{
    @Autowired
    ActionRepository actionRepository;

    public String create(Action action) {
        actionRepository.save(action);
        return "Action created";
    }

    public String update(Action action) {
        actionRepository.save(action);
        return "Action updated";
    }

    public String delete(Integer integer) {
        actionRepository.deleteById(integer);
        return "Action deleted";
    }

    public Action get(Integer integer) {
        return actionRepository.findById(integer).get();
    }

    public List<Action> listAll() {
        return actionRepository.findAll();
    }
}
