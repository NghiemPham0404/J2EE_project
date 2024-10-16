package com.example.J2EE_project.controllers;


import com.example.J2EE_project.models.Role;
import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllRoles(){
        return ResponseBuilder.buildResponse(roleService.listAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createRole(@RequestBody Role role) {
        return ResponseBuilder.buildResponse(roleService.create(role), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateRole(@PathVariable("id") int id,@RequestBody Role role) {
        roleService.get(id);
        return ResponseBuilder.buildResponse(roleService.update(role), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable("id") int id) {
       return ResponseBuilder.buildResponse(roleService.delete(id), HttpStatus.OK);
    }

}
