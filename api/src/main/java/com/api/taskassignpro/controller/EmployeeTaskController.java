package com.api.taskassignpro.controller;

import com.api.taskassignpro.model.employeeTask.EmployeeTaskDTO;
import com.api.taskassignpro.service.EmployeeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeTaskController {

    @Autowired
    private EmployeeTaskService service;

    @GetMapping("/employeetask/{id}")
    public ResponseEntity<?> findEmployeeTaskById(@PathVariable Long id) {
        try {
            EmployeeTaskDTO dto = service.findEmployeeTaskById(id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("employeetask")
    public ResponseEntity<?> findAllEmployeeTask() {
        try {
            List<EmployeeTaskDTO> list = service.findAllEmployeeTask();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/employeetask")
    public ResponseEntity<?> saveEmployeeTask(@RequestBody EmployeeTaskDTO dto) {
        try {
            service.saveEmployeeTask(dto);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/employeetask/{id}")
    public ResponseEntity<?> deleteEmployeeTask(@PathVariable Long id) {
        try {
            service.deleteEmployeeTask(id);
            return ResponseEntity.status(HttpStatus.OK).body("Content deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/employeetask/employee/{id}")
    public ResponseEntity<?> findByEmployeeId(@PathVariable Long id) {
        try {
            List<EmployeeTaskDTO> list = service.findByEmployeeId(id);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/employeetask/task/{id}")
    public ResponseEntity<?> findByTaskId(@PathVariable Long id) {
        try {
            List<EmployeeTaskDTO> list = service.findByTaskId(id);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
