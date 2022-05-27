package ru.liga.application.api;

import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Task;

public interface TaskService {
    TaskDto create(TaskDto taskDto);

    void delete(String uuid);

    Task findByUuid(String uuid);

    void update(String uuid, TaskDto taskDto);
}
