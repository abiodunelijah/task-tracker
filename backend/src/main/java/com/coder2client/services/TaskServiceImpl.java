package com.coder2client.services;

import com.coder2client.entities.Task;
import com.coder2client.entities.TaskList;
import com.coder2client.repositories.TaskListRepository;
import com.coder2client.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task already has an ID.");
        }

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task List does not exist"));

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title is null or empty.");
        }

        if (task.getDescription() == null || task.getDescription().isBlank()) {
            throw new IllegalArgumentException("Task description is null or empty.");
        }

        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task status is required.");
        }

        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task priority is required.");
        }

        LocalDateTime now = LocalDateTime.now();
        Task newTask = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                taskList,
                task.getPriority(),
                now,
                now
        );

        return taskRepository.save(newTask);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() != null && !task.getId().equals(taskId)) {
            throw new IllegalArgumentException("Task id does not match.");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task does not exist"));

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title is null or empty.");
        }

        if (task.getDescription() == null || task.getDescription().isBlank()) {
            throw new IllegalArgumentException("Task description is null or empty.");
        }

        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task status is required.");
        }

        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task priority is required.");
        }

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setUpdatedDate(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        Task task = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task does not exist"));
        taskRepository.delete(task);
    }
}
