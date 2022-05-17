package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.api.MessageService;
import ru.liga.application.api.TaskValidatorService;
import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.type.Message;
import ru.liga.application.exception.CustomValidationException;

@Service
@RequiredArgsConstructor
public class TaskValidatorServiceImpl implements TaskValidatorService {
    private final MessageService messageService;

    @Override
    public void validate(TaskDto taskDto) {
        String description = taskDto.getDescription();
        if (description.isEmpty()) {
            throw new CustomValidationException(messageService.getMessage(Message.EMPTY_TASK_DESCRIPTION));
        }
    }
}
