package com.coder2client.controllers;

import com.coder2client.mappers.TaskMapper;
import com.coder2client.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TasksController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    
}
