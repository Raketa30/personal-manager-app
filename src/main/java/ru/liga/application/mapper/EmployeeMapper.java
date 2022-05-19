package ru.liga.application.mapper;

import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Employee;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    private EmployeeMapper() {
    }

    public static Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                //todo эт плохо) у тебя в маппере какая то логика происходит такое лучше где в другом месте делать
                // done
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .salary(employeeDto.getSalary())
                .tasks(new HashSet<>())
                .build();
    }

    public static EmployeeDto employeeToEmployeeDto(Employee employee) {
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

    private static List<TaskDto> getTaskDtoList(Employee employee) {
        return employee.getTasks().stream()
                .map(TaskMapper::taskToTaskDto)
                .collect(Collectors.toList());
    }
}
