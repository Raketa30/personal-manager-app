package ru.liga.application.api;

import ru.liga.application.domain.soap.employee.EmployeeDto;

import java.util.List;

public interface EmployeeDtoChecker {
    List<String> check(EmployeeDto dto);

    //todo перенести в класс checker
    // done
    boolean checkDtoIdEqualsZero(EmployeeDto employeeDto);
}
