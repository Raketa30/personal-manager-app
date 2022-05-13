package ru.liga.application.api;

import ru.liga.application.domain.entity.Position;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.exception.PositionValidatorException;

public interface PositionValidatorService {
    void validate(Position position, EmployeeDto employeeDto) throws PositionValidatorException;
}
