package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.api.MessageService;
import ru.liga.application.api.PositionValidatorService;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.entity.Position;
import ru.liga.application.exception.CustomValidationException;

import static ru.liga.application.domain.type.Message.SALARY_NOT_IN_POSITION_RANGE;

@Service
@RequiredArgsConstructor
public class PositionValidatorServiceImpl implements PositionValidatorService {
    private final MessageService messageService;

    @Override
    public void validate(Position position, EmployeeDto employeeDto) {
        int salary = employeeDto.getSalary();
        if (checkWrongPositionSalary(position, salary)) {
            throw new CustomValidationException(getWrongSalaryMessage(position, salary));
        }
    }

    private boolean checkWrongPositionSalary(Position position, int salary) {
        return salary < position.getMinSalary() || salary > position.getMaxSalary();
    }

    private String getWrongSalaryMessage(Position position, int salary) {
        String message = messageService.getMessage(SALARY_NOT_IN_POSITION_RANGE);
        return String.format(message,
                position.getTitle(),
                position.getMinSalary(),
                position.getMaxSalary(),
                salary
        );
    }
}
