package ru.liga.application.mapper;

import lombok.AllArgsConstructor;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Employee;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@AllArgsConstructor
//todo можно сделать не бином
// done
public class EmployeeMapper {
    private final TaskMapper taskMapper;

    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .uuid(UUID.randomUUID().toString())
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .salary(employeeDto.getSalary())
                .tasks(new HashSet<>())
                .build();
    }

    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setUuid(employee.getUuid());
        employeeDto.setFirstname(employee.getFirstname());
        employeeDto.setLastname(employee.getLastname());
        employeeDto.setPositionTitle(employee.getPosition().getTitle());
        employeeDto.setDepartmentTitle(employee.getPosition().getDepartment().getTitle());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setTasks(getTaskDtoList(employee));
        return employeeDto;
    }

    private List<TaskDto> getTaskDtoList(Employee employee) {
        return employee.getTasks().stream()
                .map(taskMapper::taskToTaskDto)
                .collect(Collectors.toList());
    }
}
