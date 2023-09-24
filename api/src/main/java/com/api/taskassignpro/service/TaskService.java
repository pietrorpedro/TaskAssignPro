package com.api.taskassignpro.service;

import com.api.taskassignpro.model.employee.Employee;
import com.api.taskassignpro.model.employee.EmployeeDTO;
import com.api.taskassignpro.model.task.Task;
import com.api.taskassignpro.model.task.TaskDTO;
import com.api.taskassignpro.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    // GET

    public TaskDTO findTaskById(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found task with id " + id));

        return new TaskDTO(task.getTitle(), task.getBody(), task.isDone());
    }

    public List<TaskDTO> findAllTask() {
        List<Task> tasks = repository.findAll();
        List<TaskDTO> taskDTOS = new ArrayList<TaskDTO>();

        for (Task task : tasks) {
            TaskDTO taskDTO = new TaskDTO(
                    task.getTitle(),
                    task.getBody(),
                    task.isDone()
            );
            taskDTOS.add(taskDTO);
        }
        return taskDTOS;
    }

    // POST

    public void saveTask(TaskDTO dto) {
        Task task = new Task();
        BeanUtils.copyProperties(dto, task);

        repository.save(task);
    }

    // PUT

    public void updateTask(TaskDTO dto, Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found task with id " + id));
        BeanUtils.copyProperties(dto, task);

        repository.save(task);
    }

    // DELETE

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
