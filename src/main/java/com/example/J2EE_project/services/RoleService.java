package com.example.J2EE_project.services;

import com.example.J2EE_project.models.Role;
import com.example.J2EE_project.models.RoleAction;
import com.example.J2EE_project.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO : Service này dùng để thực hiện việc thêm, xóa sửa một Role(Vai trò)
 * */

@Service
public class RoleService implements serviceInterface<Role, Integer> {
    @Autowired
    private RoleRepository roleRepository;

    public void createRoleWithActions(Role role) {
    }

    @Override
    public String create(Role role) {
        roleRepository.save(role);
        return "Role created";
    }

    @Override
    public String update(Role role) {
        roleRepository.save(role);
        return "Role updated";
    }

    @Override
    public String delete(Integer integer) {
        roleRepository.deleteById(integer);
        return "Role deleted";
    }

    @Override
    public Role get(Integer integer) {
        return roleRepository.findById(integer).get();
    }

    @Override
    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> listAllNewest() {
        return List.of();
    }

    @Override
    public Page<Role> listAll(int page) {
        return null;
    }

    @Override
    public Page<Role> listAllNewest(int page) {
        return null;
    }
}
