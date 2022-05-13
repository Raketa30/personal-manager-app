package ru.liga.application.mapper;

import org.springframework.stereotype.Component;
import ru.liga.application.api.EmployeeMapper;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.soap.employee.EmployeeDto;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {
    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        Long id = employeeDto.getId() == 0 ? null : employeeDto.getId();
        return Employee.builder()
                .id(id)
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .salary(employeeDto.getSalary())
                .build();
    }

    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstname(employee.getFirstname());
        employeeDto.setLastname(employee.getLastname());
        employeeDto.setPositionTitle(employee.getPosition().getTitle());
        employeeDto.setDepartmentTitle(employee.getPosition().getDepartment().getTitle());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }
}
