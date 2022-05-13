package ru.liga.application.api;

import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.soap.employee.EmployeeDto;

//todo в пакет api
// done
public interface EmployeeMapper {
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);
}
