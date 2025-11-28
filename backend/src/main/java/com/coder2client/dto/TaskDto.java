package com.coder2client.dto;

import com.coder2client.enums.TaskPriority;
import com.coder2client.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto (UUID id,
                       String title ,
                       String description,
                       LocalDateTime dueDate,
                       TaskPriority priority,
                       TaskStatus status){

}
