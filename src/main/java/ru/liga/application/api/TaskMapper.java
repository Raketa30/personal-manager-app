package ru.liga.application.api;

import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Task;

public interface TaskMapper {
    TaskDto taskToTaskDto(Task task);
}
