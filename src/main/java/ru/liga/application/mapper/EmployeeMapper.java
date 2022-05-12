package ru.liga.application.mapper;

import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.soap.employee.EmployeeDto;

public interface EmployeeMapper {//todo в пакет api
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);
}
