package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;

public interface EmployeeValidatorService {
    void validate(EmployeeDto employeeDto);
}
