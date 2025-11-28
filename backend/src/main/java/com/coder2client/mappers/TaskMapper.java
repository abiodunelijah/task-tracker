package com.coder2client.mappers;

import com.coder2client.dto.TaskDto;
import com.coder2client.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto  taskDto);

    TaskDto toDto(Task task);
}
