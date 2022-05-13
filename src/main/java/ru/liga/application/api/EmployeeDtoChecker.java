package ru.liga.application.api;

import ru.liga.application.domain.soap.employee.EmployeeDto;

import java.util.List;

public interface EmployeeDtoChecker {
    List<String> check(EmployeeDto dto);

    boolean checkDtoIdEqualsZero(EmployeeDto employeeDto);
}
