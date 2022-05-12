package ru.liga.application.mapper;

import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.soap.employee.EmployeeDto;

public interface EmployeeMapper {
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);
}
