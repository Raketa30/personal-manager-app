package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    @SneakyThrows
    public void validateRegistration(EmployeeDto dto) {
        //todo в отдельный чекер
        // done
        if (!checkDtoIdEqualsZero(dto)) {
            String message = messageService.getMessage(WRONG_ID_DURING_REGISTRATION);
            throw new EmployeeValidatorException(message);
        }
        validateFields(dto);
    }

    @Override
    @SneakyThrows
    public void validateUpdate(EmployeeDto dto) {
        //todo в отдельный чекер
        // done
        if (checkDtoIdEqualsZero(dto)) {
            String message = messageService.getMessage(WRONG_ID_DURING_UPDATE);
            throw new EmployeeValidatorException(message);
        }
        validateFields(dto);
    }

    private String getMessage(List<String> checkerMessages) {
        StringBuilder sb = new StringBuilder();
        checkerMessages.forEach(msg -> sb.append(msg).append("\n"));
        return sb.toString();
    }

    @SneakyThrows
    private void validateFields(EmployeeDto dto) {
        List<String> checkerMessages = dtoChecker.check(dto);
        if (!checkerMessages.isEmpty()) {
            throw new EmployeeValidatorException(getMessage(checkerMessages));
        }
    }

    private boolean checkDtoIdEqualsZero(EmployeeDto employeeDto) {
        return employeeDto.getId() == 0;
    }
}
