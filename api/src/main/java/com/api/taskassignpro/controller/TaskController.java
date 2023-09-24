package com.api.taskassignpro.controller;

import com.api.taskassignpro.model.task.TaskDTO;
import com.api.taskassignpro.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/task/{id}")
    public ResponseEntity<?> findTaskById(@PathVariable Long id) {
        try {
            TaskDTO taskDTO = service.findTaskById(id);
            return ResponseEntity.status(HttpStatus.OK).body(taskDTO);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/task")
    public ResponseEntity<?> findAllTask() {
        try {
            List<TaskDTO> list = service.findAllTask();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/task")
    public ResponseEntity<?> saveTask(@RequestBody TaskDTO dto) {
        try {
            service.saveTask(dto);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO dto, @PathVariable Long id) {
        try {
            service.updateTask(dto, id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            service.deleteTask(id);
            return ResponseEntity.status(HttpStatus.OK).body("Content deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

}
