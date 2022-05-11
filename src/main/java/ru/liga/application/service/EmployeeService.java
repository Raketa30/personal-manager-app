package ru.liga.application.service;

import ru.liga.application.domain.soap.employee.EmployeeDto;

import java.util.Collection;

public interface EmployeeService { //todo вынести интерфейс в отдельный пакет api
    void delete(long employeeId);

    void deleteLastRow();

    Collection<EmployeeDto> findAll();

    String save(EmployeeDto employeeDto);

    String update(EmployeeDto employeeDto);

}
