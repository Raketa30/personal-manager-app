package ru.liga.application.service.validation;

import ru.liga.application.domain.soap.employee.EmployeeDto;

import java.util.List;

public interface ValidatorService {
    List<String> validateRegistration(EmployeeDto employeeDto);

    List<String> validateUpdate(EmployeeDto employeeDto);
}
