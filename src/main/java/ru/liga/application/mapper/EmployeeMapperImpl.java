package ru.liga.application.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.application.api.EmployeeMapper;
import ru.liga.application.api.TaskMapper;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Employee;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EmployeeMapperImpl implements EmployeeMapper { //todo можно сделать не бином
    private final TaskMapper taskMapper;

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        Long id = employeeDto.getId() == 0 ? null : employeeDto.getId();
        return Employee.builder()
                .id(id)
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .salary(employeeDto.getSalary())
                .tasks(new HashSet<>())
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
        employeeDto.setTasks(getTaskDtoList(employee));
        return employeeDto;
    }

    private List<TaskDto> getTaskDtoList(Employee employee) {
        return employee.getTasks().stream()
                .map(taskMapper::taskToTaskDto)
                .collect(Collectors.toList());
    }
}
