package ru.liga.application.mapper;

import org.springframework.stereotype.Component;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.EmployeePosition;
import ru.liga.application.domain.soap.employee.EmployeeDto;

import java.util.function.Function;

@Component
public class EmployeeMapper {
    public Employee build(EmployeeDto employeeDto, EmployeePosition employeePosition) {
        return Employee.builder()
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .salary(employeeDto.getSalary())
                .employeePosition(employeePosition)
                .build();
    }

    public Function<Employee, EmployeeDto> getFunctionDtoToEntity() {
        return employee -> {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setFirstname(employee.getFirstname());
            employeeDto.setLastname(employee.getLastname());
            employeeDto.setPositionTitle(employee.getEmployeePosition().getTitle());
            employeeDto.setDepartmentTitle(employee.getEmployeePosition().getDepartment().getTitle());
            employeeDto.setSalary(employee.getSalary());
            return employeeDto;
        };
    }

    public Employee update(EmployeeDto employeeDto, EmployeePosition employeePosition) {
        return Employee.builder()
                .id(employeeDto.getId())
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .salary(employeeDto.getSalary())
                .employeePosition(employeePosition)
                .build();
    }

}
