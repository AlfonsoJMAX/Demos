package com.example.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanagement.dto.CreateTaskRequest;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.TaskStatus;
import com.example.taskmanagement.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(CreateTaskRequest request) {
        Task task = new Task();
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setDueDate(LocalDateTime.parse(request.getDueDate())); // Convertimos String a LocalDateTime
        task.setStatus(TaskStatus.PENDING);

        if (request.getParentTaskId() != null) {
            Optional<Task> parentTask = taskRepository.findById(request.getParentTaskId());
            parentTask.ifPresent(task::setParentTask);
        }

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task markTaskAsCompleted(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        markTaskAndSubtasksAsCompleted(task);
        return taskRepository.save(task);
    }

    private void markTaskAndSubtasksAsCompleted(Task task) {
        task.setStatus(TaskStatus.COMPLETED);
        if (task.getSubtasks() != null) {
            task.getSubtasks().forEach(this::markTaskAndSubtasksAsCompleted);
        }
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
}
