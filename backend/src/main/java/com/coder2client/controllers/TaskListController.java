package com.coder2client.controllers;

import com.coder2client.dto.TaskListDto;
import com.coder2client.entities.TaskList;
import com.coder2client.mappers.TaskListMapper;
import com.coder2client.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @GetMapping
    public ResponseEntity<List<TaskListDto>> listTaskLists() {
        List<TaskListDto> taskListDtos = taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
        return ResponseEntity.ok(taskListDtos);
    }

    @PostMapping
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList taskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskListMapper.toDto(taskList));
    }

    @GetMapping("/{task_list_id}")
    public ResponseEntity<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskList(taskListId)
                .map(taskListMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{task_list_id}")
    public ResponseEntity<TaskListDto> updateTaskList(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskListDto taskListDto) {
        TaskList taskList = taskListService.updateTaskList(taskListId, taskListMapper.fromDto(taskListDto));
        return ResponseEntity.ok(taskListMapper.toDto(taskList));
    }

    @DeleteMapping("/{task_list_id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
        return ResponseEntity.noContent().build();
    }

}
