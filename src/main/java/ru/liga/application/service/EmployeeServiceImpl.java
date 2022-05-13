package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.api.*;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.Position;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.exception.EmployeeNotFoundException;
import ru.liga.application.exception.EmployeeValidatorException;
import ru.liga.application.exception.PositionValidatorException;
import ru.liga.application.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.liga.application.domain.type.Message.EMPLOYEE_NOT_FOUND;
import static ru.liga.application.domain.type.Message.POSITION_NOT_FOUND;

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
    public EmployeeDto save(EmployeeDto employeeDto) throws EmployeeValidatorException {
        employeeValidatorService.validateRegistration(employeeDto);
        Employee createdEmployee = mapper.employeeDtoToEmployee(employeeDto);
        Position position = findEmployeePosition(employeeDto);
        createdEmployee.setPosition(position);
        Employee employee = employeeRepository.save(createdEmployee);
        return mapper.employeeToEmployeeDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto) throws EmployeeValidatorException {
        employeeValidatorService.validateUpdate(employeeDto);
        Employee employee = findEmployeeById(employeeDto.getId());
        updateFields(employee, employeeDto);
        employeeRepository.save(employee);
        return mapper.employeeToEmployeeDto(employee);
    }

    private Employee findEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(messageService.getMessage(EMPLOYEE_NOT_FOUND)));
    }

    private Position findEmployeePosition(EmployeeDto employeeDto) {
        Position position = positionService.findByTitleAndDepartmentTitle(employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle());
        try {
            positionValidatorService.validate(position, employeeDto);
        } catch (PositionValidatorException e) {
            throw new EmployeeNotFoundException(messageService.getMessage(POSITION_NOT_FOUND), e);
        }
        return position;
    }

    private void updateFields(Employee employee, EmployeeDto dto) {
        Position position = findEmployeePosition(dto);
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setSalary(dto.getSalary());
        employee.setPosition(position);
    }
}
