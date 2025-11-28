package com.coder2client.mappers;

import com.coder2client.dto.TaskListDto;
import com.coder2client.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
