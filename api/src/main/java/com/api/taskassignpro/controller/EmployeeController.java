package com.api.taskassignpro.controller;

import com.api.taskassignpro.model.employee.EmployeeDTO;
import com.api.taskassignpro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDTO dto = service.findEmployeeById(id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<?> findAllEmployees() {
        try {
            List<EmployeeDTO> list = service.findAllEmployees();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO dto) {
        try {
            service.saveEmployee(dto);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO dto, @PathVariable Long id) {
        try {
            service.updateEmployee(dto, id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            service.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.OK).body("Content deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
