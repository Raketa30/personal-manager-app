package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;

import java.util.List;

public interface PositionValidatorService {
    List<String> validate(EmployeeDto employeeDto);
}
