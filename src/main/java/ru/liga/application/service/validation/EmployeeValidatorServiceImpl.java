package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.domain.entity.EmployeePosition;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.service.EmployeePositionService;
import ru.liga.application.service.MessageService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.liga.application.common.Message.*;

@Service
@RequiredArgsConstructor
public class EmployeeValidatorServiceImpl implements ValidatorService {
    private static final long NEW_USER_ID = 0;
    private final EmployeePositionService positionService;
    private final MessageService messageService;

    @Override
    public List<String> validateRegistration(EmployeeDto employeeDto) {
        List<String> messages = new ArrayList<>();
        if (employeeDto.getId() != NEW_USER_ID) {
            messages.add(messageService.getMessage(WRONG_ID_DURING_REGISTRATION));
        }
        messages.addAll(validatePosition(employeeDto));
        messages.addAll(checkDtoEmptyFields(employeeDto));
        return messages;
    }

    @Override
    public List<String> validateUpdate(EmployeeDto employeeDto) {
        List<String> messages = new ArrayList<>();
        if (employeeDto.getId() == NEW_USER_ID) {
            messages.add(messageService.getMessage(WRONG_ID_DURING_UPDATE));
        }
        messages.addAll(validatePosition(employeeDto));
        messages.addAll(checkDtoEmptyFields(employeeDto));
        return messages;
    }

    private List<String> checkDtoEmptyFields(EmployeeDto employeeDto) {
        List<String> messages = new ArrayList<>();
        if (employeeDto.getFirstname().isEmpty()) {
            messages.add(messageService.getMessage(EMPTY_FIRSTNAME));
        }
        if (employeeDto.getLastname().isEmpty()) {
            messages.add(messageService.getMessage(EMPTY_LASTNAME));
        }
        if (employeeDto.getPositionTitle().isEmpty()) {
            messages.add(messageService.getMessage(EMPTY_POSITION));
        }
        if (employeeDto.getDepartmentTitle().isEmpty()) {
            messages.add(messageService.getMessage(EMPTY_DEPARTMENT));
        }
        return messages;
    }

    private boolean checkWrongPositionSalary(EmployeePosition employeePosition, int salary) {
        return salary < employeePosition.getMinSalary() || salary > employeePosition.getMaxSalary();
    }

    private String getWrongSalaryMessage(EmployeePosition employeePosition, int salary) {
        String message = messageService.getMessage(SALARY_NOT_IN_POSITION_RANGE);
        return String.format(message,
                employeePosition.getTitle(),
                employeePosition.getMinSalary(),
                employeePosition.getMaxSalary(),
                salary
        );
    }

    private List<String> validatePosition(EmployeeDto employeeDto) {
        EmployeePosition employeePosition = positionService.findByTitleAndDepartmentTitle(
                employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle()
        );
        int salary = employeeDto.getSalary();
        if (checkWrongPositionSalary(employeePosition, salary)) {
            return Collections.singletonList(getWrongSalaryMessage(employeePosition, salary));
        }
        return Collections.emptyList();
    }
}
