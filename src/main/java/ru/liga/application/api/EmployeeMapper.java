package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.entity.Employee;


public interface EmployeeMapper {
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);
}
