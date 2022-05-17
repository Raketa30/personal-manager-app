package ru.liga.application.api;

import ru.liga.application.domain.dto.TaskDto;

public interface TaskValidatorService {
    void validate(TaskDto taskDto);
}
