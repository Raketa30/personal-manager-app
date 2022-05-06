package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.domain.entity.EmployeePosition;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.service.EmployeePositionService;

import java.util.Optional;

import static ru.liga.application.common.Message.POSITION_NOT_FOUND;
import static ru.liga.application.common.Message.SALARY_NOT_IN_POSITION_RANGE;

@Service
@RequiredArgsConstructor
public class EmployeeValidatorService {
    private final EmployeePositionService positionService;

    public boolean validateRegistration(EmployeeDto employeeDto) {
        validatePosition(employeeDto);
        return employeeDto.getId() == 0 &&
                employeeDto.getFirstname().isEmpty() &&
                employeeDto.getLastname().isEmpty() &&
                employeeDto.getPositionTitle().isEmpty() &&
                employeeDto.getDepartmentTitle().isEmpty();
    }

    public boolean validateUpdate(EmployeeDto employeeDto) {
        validatePosition(employeeDto);
        return employeeDto.getId() != 0 &&
                employeeDto.getFirstname().isEmpty() &&
                employeeDto.getLastname().isEmpty() &&
                employeeDto.getPositionTitle().isEmpty() &&
                employeeDto.getDepartmentTitle().isEmpty();
    }

    private boolean checkWrongPositionSalary(EmployeePosition employeePosition, int salary) {
        return salary < employeePosition.getMinSalary()
                || salary > employeePosition.getMaxSalary();
    }

    private String getWrongSalaryMessage(EmployeePosition employeePosition, int salary) {
        return String.format(SALARY_NOT_IN_POSITION_RANGE,
                employeePosition.getTitle(),
                employeePosition.getMinSalary(),
                employeePosition.getMaxSalary(),
                salary
        );
    }

    private boolean validatePosition(EmployeeDto employeeDto) {
        Optional<EmployeePosition> positionOptional =
                positionService.findByTitleAndDepartmentTitle(
                        employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle()
                );
        if (positionOptional.isEmpty()) {
            throw new IllegalArgumentException(POSITION_NOT_FOUND);
        }
        EmployeePosition employeePosition = positionOptional.get();
        int salary = employeeDto.getSalary();
        if (checkWrongPositionSalary(employeePosition, salary)) {
            throw new IllegalArgumentException(getWrongSalaryMessage(employeePosition, salary));
        }
        return true;
    }


}
