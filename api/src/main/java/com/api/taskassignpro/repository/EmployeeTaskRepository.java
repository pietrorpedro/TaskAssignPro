package com.api.taskassignpro.repository;

import com.api.taskassignpro.model.employeeTask.EmployeeTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {

    List<EmployeeTask> findByEmployeeId(Long id);
    List<EmployeeTask> findByTaskId(Long id);
}
