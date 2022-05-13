package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.api.EmployeeDtoChecker;
import ru.liga.application.api.EmployeeValidatorService;
import ru.liga.application.api.MessageService;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.exception.EmployeeValidatorException;

import java.util.List;

import static ru.liga.application.domain.type.Message.WRONG_ID_DURING_REGISTRATION;
import static ru.liga.application.domain.type.Message.WRONG_ID_DURING_UPDATE;

@Service
@RequiredArgsConstructor
public class EmployeeValidatorServiceImpl implements EmployeeValidatorService {
    private final MessageService messageService;
    private final EmployeeDtoChecker dtoChecker;

    @Override
    public void validateRegistration(EmployeeDto dto) throws EmployeeValidatorException {
        if (!dtoChecker.checkDtoIdEqualsZero(dto)) {
            String message = messageService.getMessage(WRONG_ID_DURING_REGISTRATION);
            throw new EmployeeValidatorException(message);
        }
        validateFields(dto);
    }

    @Override
    public void validateUpdate(EmployeeDto dto) throws EmployeeValidatorException {
        if (dtoChecker.checkDtoIdEqualsZero(dto)) {
            String message = messageService.getMessage(WRONG_ID_DURING_UPDATE);
            throw new EmployeeValidatorException(message);
        }
        validateFields(dto);
    }

    private String getMessage(List<String> checkerMessages) {
        //todo плохое название)) Опиши что конкретно оно делает. То есть есди это просто выдача сообщения то message
        // done
        StringBuilder messageBuilder = new StringBuilder();
        checkerMessages.forEach(msg -> messageBuilder.append(msg).append("\n"));
        return messageBuilder.toString();
    }

    private void validateFields(EmployeeDto dto) throws EmployeeValidatorException {
        List<String> checkerMessages = dtoChecker.check(dto);
        if (!checkerMessages.isEmpty()) {
            throw new EmployeeValidatorException(getMessage(checkerMessages));
        }
    }
}
