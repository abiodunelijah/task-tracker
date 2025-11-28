package com.coder2client.services;

import com.coder2client.entities.TaskList;
import com.coder2client.repositories.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {

        if (taskList.getId() != null) {
            throw  new IllegalArgumentException("Task List already has an ID.");
        }

        if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw  new IllegalArgumentException("Task List title is null or empty.");
        }

        if (taskList.getDescription() == null || taskList.getDescription().isBlank()) {
            throw  new IllegalArgumentException("Task List description is null or empty.");
        }

        LocalDateTime now =  LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));

    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID id, TaskList taskList) {

        if (taskList.getId() != null && !Objects.equals(taskList.getId(), id)){
            throw  new IllegalArgumentException("Task List id does not match.");
        }

        TaskList existingTaskList = taskListRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Task List id does not exist"));

        if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw  new IllegalArgumentException("Task List title is null or empty.");
        }

        if (taskList.getDescription() == null || taskList.getDescription().isBlank()) {
            throw  new IllegalArgumentException("Task List description is null or empty.");
        }

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdatedDate(LocalDateTime.now());

        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID id) {
        taskListRepository.deleteById(id);
    }
}
