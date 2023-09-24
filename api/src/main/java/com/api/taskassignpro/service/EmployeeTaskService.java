package com.api.taskassignpro.service;

import com.api.taskassignpro.model.employeeTask.EmployeeTask;
import com.api.taskassignpro.model.employeeTask.EmployeeTaskDTO;
import com.api.taskassignpro.repository.EmployeeTaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeTaskService {

    @Autowired
    private EmployeeTaskRepository repository;

    // GET

    public EmployeeTaskDTO findEmployeeTaskById(Long id) {
        EmployeeTask employeeTask = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found employee task with id " + id));

        return new EmployeeTaskDTO(employeeTask.getEmployeeId(), employeeTask.getTaskId());
    }

    public List<EmployeeTaskDTO> findAllEmployeeTask() {
        List<EmployeeTask> employeeTasks = repository.findAll();
        List<EmployeeTaskDTO> employeeTaskDTOS = new ArrayList<EmployeeTaskDTO>();

        for (EmployeeTask employeeTask : employeeTasks) {
            EmployeeTaskDTO employeeTaskDTO = new EmployeeTaskDTO(
                    employeeTask.getEmployeeId(),
                    employeeTask.getTaskId()
            );
            employeeTaskDTOS.add(employeeTaskDTO);
        }
        return employeeTaskDTOS;
    }

    // POST

    public void saveEmployeeTask(EmployeeTaskDTO dto) {
        EmployeeTask employeeTask = new EmployeeTask();
        BeanUtils.copyProperties(dto, employeeTask);

        repository.save(employeeTask);
    }

    // PUT

    public void updateEmployeeTask(EmployeeTaskDTO dto, Long id) {
        EmployeeTask employeeTask = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found employee task with id " + id));
        BeanUtils.copyProperties(dto, employeeTask);

        repository.save(employeeTask);
    }

    // DELETE

    public void deleteEmployeeTask(Long id) {
        repository.deleteById(id);
    }
}
