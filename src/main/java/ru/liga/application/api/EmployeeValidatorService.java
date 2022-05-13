package ru.liga.application.api;

import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.exception.EmployeeValidatorException;

public interface EmployeeValidatorService {
    void validateRegistration(EmployeeDto employeeDto) throws EmployeeValidatorException;

    void validateUpdate(EmployeeDto employeeDto) throws EmployeeValidatorException;
}
