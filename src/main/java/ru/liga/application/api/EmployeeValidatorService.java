package ru.liga.application.api;

import ru.liga.application.domain.soap.employee.EmployeeDto;

//todo в пакет api
// done
public interface EmployeeValidatorService {
    void validateRegistration(EmployeeDto employeeDto);

    void validateUpdate(EmployeeDto employeeDto);
}
