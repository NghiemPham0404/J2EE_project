package com.example.J2EE_project.controllers;


import com.example.J2EE_project.models.Role;
import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Operation(summary = "Lấy tất cả Role")
    @GetMapping("/all")
    public ResponseEntity<Object> getAllRoles() {
        return ResponseBuilder.buildResponse(roleService.listAll(), HttpStatus.OK);
    }

    @Operation(summary = "Lấy role dựa trên id")
    @GetMapping("/{id}")
    public Role getAllRoles(@PathVariable("id") int id) {
        return roleService.get(id);
    }

    @Operation(summary = "Tạo một role")
    @PostMapping
    public ResponseEntity<Object> createRole(@RequestBody Role role) {
        return ResponseBuilder.buildResponse(roleService.create(role), HttpStatus.OK);
    }

    @Operation(summary = "Sửa một role")
    @PutMapping("{id}")
    public ResponseEntity<Object> updateRole(@PathVariable("id") int id, @RequestBody Role role) {
        roleService.get(id);
        return ResponseBuilder.buildResponse(roleService.update(role), HttpStatus.OK);
    }

    @Operation(summary = "Lấy role dựa trên id")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable("id") int id) {
        return ResponseBuilder.buildResponse(roleService.delete(id), HttpStatus.OK);
    }

}
