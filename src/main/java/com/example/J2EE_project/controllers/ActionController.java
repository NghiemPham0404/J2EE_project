package com.example.J2EE_project.controllers;


import com.example.J2EE_project.models.Action;
import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.ActionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actions")
public class ActionController {

    @Autowired
    private ActionService actionService;

    /**
     * TODO : Thêm mới một Action
     */
    @Operation(summary = "Thêm mới một Action")
    @PostMapping
    public ResponseEntity<Object> createAction(@RequestBody Action action) {
        String response = actionService.create(action);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

   /**
     * TODO : Sửa một Action
     */
   @Operation(summary = "Sửa một Action")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAction(@PathVariable Integer id, @RequestBody Action action) {
        actionService.get(id);
        String response = actionService.update(action);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    /**
     * TODO : Xoá một Action
     */
    @Operation(summary = "Xóa một Action")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAction(@PathVariable Integer id) {
        String response = actionService.delete(id);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

     /**
     * TODO : Lấy một Action bằng Id
     */
    @GetMapping("/{id}")
    public Action getActionById(@PathVariable Integer id) {
        return actionService.get(id);
    }

     /**
     * TODO : Lấy hết tất cả các Action
     */
    @GetMapping("/all")
    public ResponseEntity<Object> listAllActions() {
        List<Action> actions = actionService.listAll();
        return ResponseBuilder.buildResponse(actions, HttpStatus.OK);
    }
}
