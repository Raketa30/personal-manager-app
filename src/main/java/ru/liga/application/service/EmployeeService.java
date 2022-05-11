package ru.liga.application.service;

import ru.liga.application.domain.soap.employee.EmployeeDto;

import java.util.Collection;

public interface EmployeeService {
    void delete(long employeeId);

    void deleteLastRow();

    Collection<EmployeeDto> findAll();

    EmployeeDto findById(long id);

    String save(EmployeeDto employeeDto);

    String update(EmployeeDto employeeDto);

}
