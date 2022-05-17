package ru.liga.application.mapper;

import org.springframework.stereotype.Component;
import ru.liga.application.api.TaskMapper;
import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Task;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public TaskDto taskToTaskDto(Task task) {
        return TaskDto.builder()
                .employeeId(task.getEmployee().getId())
                .uuid(task.getUuid())
                .completed(task.isCompleted())
                .description(task.getDescription())
                .build();
    }
}
