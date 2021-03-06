package ru.liga.application.mapper;

import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Task;

public class TaskMapper {
    private TaskMapper() {
    }

    public static TaskDto taskToTaskDto(Task task) {
        return TaskDto.builder()
                .employeeUuid(task.getEmployee().getUuid())
                .uuid(task.getUuid())
                .completed(task.isCompleted())
                .description(task.getDescription())
                .build();
    }
}
