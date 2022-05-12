package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.api.EmployeeService;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.EmployeePosition;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.mapper.EmployeeMapperImpl;
import ru.liga.application.repository.EmployeeRepository;
import ru.liga.application.service.validation.ValidatorService;

import java.util.List;
import java.util.stream.Collectors;

import static ru.liga.application.common.Message.EMPLOYEE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeePositionService positionService;
    private final MessageService messageService;
    private final EmployeeMapperImpl mapper;
    private final ValidatorService validationService;

    @Override
    @Transactional
    public void delete(long employeeId) {
        employeeRepository.deleteById(employeeId);
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
                    return new IllegalArgumentException(String.format(message, id));
                });
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {
        validateRegistrationDto(employeeDto);
        Employee createdEmployee = mapper.employeeDtoToEmployee(employeeDto);
        EmployeePosition employeePosition = findEmployeePosition(employeeDto);
        createdEmployee.setEmployeePosition(employeePosition);
        Employee employee = employeeRepository.save(createdEmployee);
        return mapper.employeeToEmployeeDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto) {
        validateUpdateDto(employeeDto);
        Employee createdEmployee = mapper.employeeDtoToEmployee(employeeDto);
        EmployeePosition employeePosition = findEmployeePosition(employeeDto);
        createdEmployee.setEmployeePosition(employeePosition);
        Employee employee = employeeRepository.save(createdEmployee);
        return mapper.employeeToEmployeeDto(employee);
    }

    private String buildExceptionMessage(List<String> invalidMessageList) {
        StringBuilder sb = new StringBuilder();
        invalidMessageList.forEach(err -> sb.append(err).append("\n"));
        return sb.toString();
    }

    private EmployeePosition findEmployeePosition(EmployeeDto dto) {
        return positionService.findByTitleAndDepartmentTitle(dto.getPositionTitle(), dto.getDepartmentTitle());
    }

    private void validateRegistrationDto(EmployeeDto employeeDto) {
        List<String> invalidMessageList = validationService.validateRegistration(employeeDto);
        if (!invalidMessageList.isEmpty()) {
            throw new IllegalArgumentException(buildExceptionMessage(invalidMessageList));
        }
    }

    private void validateUpdateDto(EmployeeDto employeeDto) {
        List<String> invalidMessageList = validationService.validateUpdate(employeeDto);
        if (!invalidMessageList.isEmpty()) {
            throw new IllegalArgumentException(buildExceptionMessage(invalidMessageList));
        }
    }
}
