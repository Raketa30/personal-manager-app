package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.api.*;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.Position;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.exception.EmployeeNotFoundException;
import ru.liga.application.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.liga.application.domain.type.Message.EMPLOYEE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionService positionService;
    private final MessageService messageService;
    private final EmployeeMapper mapper;
    private final EmployeeValidatorService employeeValidatorService;
    private final PositionValidatorService positionValidatorService;

    @Override
    @Transactional
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteLastRow() {
        employeeRepository.deleteLastRow();
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(mapper::employeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public EmployeeDto findById(long id) {
        return employeeRepository.findById(id)
                .map(mapper::employeeToEmployeeDto)
                .orElseThrow(() -> {
                    String message = messageService.getMessage(EMPLOYEE_NOT_FOUND);
                    return new EmployeeNotFoundException(String.format(message, id));
                });
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {
        employeeValidatorService.validateRegistration(employeeDto);
        Employee createdEmployee = mapper.employeeDtoToEmployee(employeeDto);
        Position position = findEmployeePosition(employeeDto);
        createdEmployee.setPosition(position);
        Employee employee = employeeRepository.save(createdEmployee);
        return mapper.employeeToEmployeeDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto) {
        employeeValidatorService.validateUpdate(employeeDto);
        Employee createdEmployee = mapper.employeeDtoToEmployee(employeeDto);
        Position position = findEmployeePosition(employeeDto);
        createdEmployee.setPosition(position);
        Employee employee = employeeRepository.save(createdEmployee);
        return mapper.employeeToEmployeeDto(employee);
    }

    private Position findEmployeePosition(EmployeeDto employeeDto) {
        Position position = positionService.findByTitleAndDepartmentTitle(employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle());
        positionValidatorService.validate(position, employeeDto);
        return position;
    }
}
