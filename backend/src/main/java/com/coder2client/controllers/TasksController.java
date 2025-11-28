package com.coder2client.controllers;

import com.coder2client.dto.TaskDto;
import com.coder2client.entities.Task;
import com.coder2client.mappers.TaskMapper;
import com.coder2client.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TasksController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/task-lists/{task_list_id}/tasks")
    public ResponseEntity<List<TaskDto>> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        List<TaskDto> taskDtos = taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
        return ResponseEntity.ok(taskDtos);
    }

    @PostMapping("/task-lists/{task_list_id}/tasks")
    public ResponseEntity<TaskDto> createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto) {
        Task task = taskService.createTask(taskListId, taskMapper.fromDto(taskDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.toDto(task));
    }

    @GetMapping("/task-lists/{task_list_id}/tasks/{task_id}")
    public ResponseEntity<TaskDto> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
        return taskService.getTask(taskListId, taskId)
                .map(taskMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/task-lists/{task_list_id}/tasks/{task_id}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto) {
        Task task = taskService.updateTask(taskListId, taskId, taskMapper.fromDto(taskDto));
        return ResponseEntity.ok(taskMapper.toDto(task));
    }

    @DeleteMapping("/task-lists/{task_list_id}/tasks/{task_id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
        taskService.deleteTask(taskListId, taskId);
        return ResponseEntity.noContent().build();
    }
}
