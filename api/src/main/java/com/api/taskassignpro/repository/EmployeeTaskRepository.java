package com.api.taskassignpro.repository;

import com.api.taskassignpro.model.employeeTask.EmployeeTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {
}
