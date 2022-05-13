package ru.liga.application.api;

import ru.liga.application.domain.entity.Position;
import ru.liga.application.domain.soap.employee.EmployeeDto;

public interface PositionValidatorService {
    void validate(Position position, EmployeeDto employeeDto);
}
