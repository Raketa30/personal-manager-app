package ru.liga.application.api;

import ru.liga.application.domain.dto.TaskDto;

import java.util.List;

public interface TaskValidatorService {
    List<String> validate(TaskDto taskDto);
}
