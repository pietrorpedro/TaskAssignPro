package com.api.taskassignpro.service;

import com.api.taskassignpro.model.employee.Employee;
import com.api.taskassignpro.model.employee.EmployeeDTO;
import com.api.taskassignpro.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    // GET

    public EmployeeDTO findEmployeeById(Long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found employee with id " + id));

        return new EmployeeDTO(employee.getName(), employee.getEmail(), employee.getPassword(), employee.getEmployeeType());
    }

    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employees = repository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getName(),
                    employee.getEmail(),
                    employee.getPassword(),
                    employee.getEmployeeType()
            );
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    // POST

    public void saveEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);

        repository.save(employee);
    }

    // PUT

    public void updateEmployee(EmployeeDTO dto, Long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found employee with id " + id));
        BeanUtils.copyProperties(dto, employee);

        repository.save(employee);
    }

    // DELETE

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
