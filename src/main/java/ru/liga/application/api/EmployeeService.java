package ru.liga.application.api;

import ru.liga.application.domain.soap.employee.EmployeeDto;

import java.util.Collection;

public interface EmployeeService {
    void delete(long employeeId);

    void deleteLastRow();

    Collection<EmployeeDto> findAll();

    EmployeeDto findById(long id);

    EmployeeDto save(EmployeeDto employeeDto);

    EmployeeDto update(EmployeeDto employeeDto);

}
