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
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void createRoleWithActions(Role role) {
    }

    public String create(Role role) {
        roleRepository.save(role);
        return "Role created";
    }

    public String update(Role role) {
        roleRepository.save(role);
        return "Role updated";
    }

    public String delete(Integer integer) {
        roleRepository.deleteById(integer);
        return "Role deleted";
    }

    public Role get(Integer integer) {
        return roleRepository.findById(integer).get();
    }

    public List<Role> listAll() {
        return roleRepository.findAll();
    }
}
