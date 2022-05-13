package ru.liga.application.api;

import ru.liga.application.domain.soap.employee.EmployeeDto;

public interface EmployeeValidatorService {
    void validateRegistration(EmployeeDto employeeDto);

    void validateUpdate(EmployeeDto employeeDto);
}
