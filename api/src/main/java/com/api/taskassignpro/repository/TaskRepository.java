package com.api.taskassignpro.repository;

import com.api.taskassignpro.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
