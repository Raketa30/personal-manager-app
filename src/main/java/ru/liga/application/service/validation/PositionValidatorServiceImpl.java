package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.api.MessageService;
import ru.liga.application.api.PositionService;
import ru.liga.application.api.PositionValidatorService;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.entity.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.liga.application.domain.type.Message.POSITION_NOT_FOUND;
import static ru.liga.application.domain.type.Message.SALARY_NOT_IN_POSITION_RANGE;

@Service
@RequiredArgsConstructor
public class PositionValidatorServiceImpl implements PositionValidatorService {
    private final MessageService messageService;
    private final PositionService positionService;

    @Override
    public List<String> validate(EmployeeDto employeeDto) {
        List<String> errors = new ArrayList<>();
        Optional<Position> optionalPosition =
                positionService.findByTitleAndDepartmentTitle(employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle());
        if (optionalPosition.isPresent()) {
            Position position = optionalPosition.get();
            int salary = employeeDto.getSalary();
            if (checkWrongPositionSalary(position, salary)) {
                errors.add(getWrongSalaryMessage(position, salary));
            }
        } else {
            errors.add(messageService.getMessage(POSITION_NOT_FOUND));
        }
        return errors;
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
