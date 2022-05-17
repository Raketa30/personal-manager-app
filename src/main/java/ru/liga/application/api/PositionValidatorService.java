package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.entity.Position;

public interface PositionValidatorService {
    void validate(Position position, EmployeeDto employeeDto);
}
