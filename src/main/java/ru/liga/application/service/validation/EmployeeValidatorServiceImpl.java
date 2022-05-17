package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.api.EmployeeDtoChecker;
import ru.liga.application.api.EmployeeValidatorService;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.exception.CustomValidationException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeValidatorServiceImpl implements EmployeeValidatorService {
    private final EmployeeDtoChecker dtoChecker;

    @Override
    public void validate(EmployeeDto dto){
        List<String> checkerMessages = dtoChecker.check(dto);
        if (!checkerMessages.isEmpty()) {
            throw new CustomValidationException(buildMessage(checkerMessages));
        }
    }

    private String buildMessage(List<String> checkerMessages) {
        //todo плохое название)) Опиши что конкретно оно делает. То есть есди это просто выдача сообщения то message
        // done
        StringBuilder messageBuilder = new StringBuilder();
        checkerMessages.forEach(msg -> messageBuilder.append(msg).append("\n"));
        return messageBuilder.toString();
    }

}
