package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;

import java.util.List;

public interface EmployeeValidatorService {
    List<String> validate(EmployeeDto employeeDto);
}
