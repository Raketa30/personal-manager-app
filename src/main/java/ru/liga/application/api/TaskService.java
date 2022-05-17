package ru.liga.application.api;

import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Task;

import java.util.List;

public interface TaskService {
    TaskDto create(TaskDto taskDto);

    void delete(String taskId);

    List<TaskDto> findAll(long employeeId);

    Task findByUuid(String uuid);

    void update(TaskDto taskDto);
}
