package com.coder2client.mappers;

import com.coder2client.dto.TaskDto;
import com.coder2client.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {


    @Override
    public Task fromDto(TaskDto taskDto) {

        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                null,
                taskDto.priority(),
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {

        return new TaskDto(
                task.getId(),
                task.getDescription(),
                task.getTitle(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );

    }
}
