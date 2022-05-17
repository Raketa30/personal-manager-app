package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.api.*;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.Position;
import ru.liga.application.domain.search.EmployeeSearchValues;
import ru.liga.application.exception.CustomValidationException;
import ru.liga.application.exception.NotFoundException;
import ru.liga.application.repository.EmployeeRepository;
import ru.liga.application.web.EmployeeCreateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.liga.application.domain.type.Message.EMPLOYEES_CREATED;
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
    public EmployeeDto create(EmployeeDto employeeDto) {
        employeeValidatorService.validate(employeeDto);
        Employee createdEmployee = createEmployee(employeeDto);
        Employee employee = employeeRepository.save(createdEmployee);
        return mapper.employeeToEmployeeDto(employee);
    }

    @Override
    @Transactional
    public EmployeeCreateResponse createAll(List<EmployeeDto> employeeDtoList) {
        EmployeeCreateResponse createResponse = new EmployeeCreateResponse();
        List<EmployeeDto> validatedDtoList = validateDtoList(employeeDtoList, createResponse);
        List<Employee> employeeList = createEmployeeList(validatedDtoList);
        List<Employee> createdEmployees = employeeRepository.saveAll(employeeList);
        List<EmployeeDto> createdEmployeeDto = createDtoList(createdEmployees);
        createResponse.setEmployeeDtoList(createdEmployeeDto);
        createResponse.setCreateResultMessage(String.format(messageService.getMessage(EMPLOYEES_CREATED), createdEmployees.size()));
        return createResponse;
    }

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
    public EmployeeDto findById(long id) {
        return employeeRepository.findById(id)
                .map(mapper::employeeToEmployeeDto)
                .orElseThrow(() -> {
                    String message = messageService.getMessage(EMPLOYEE_NOT_FOUND);
                    return new NotFoundException(String.format(message, id));
                });
    }

    @Override
    public Employee findEntityById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    String message = messageService.getMessage(EMPLOYEE_NOT_FOUND);
                    return new NotFoundException(String.format(message, id));
                });
    }

    @Override
    public EmployeeSearchValues getPageList(EmployeeSearchValues searchValues) {
        Page<EmployeeDto> employeeDtoPage = getEmployeeDtoPage(searchValues);
        searchValues.setPage(employeeDtoPage);
        int totalPages = employeeDtoPage.getTotalPages();
        if (totalPages > 0) {
            final int firstPage = 1;
            searchValues.setPageNumbers(
                    IntStream.rangeClosed(firstPage, totalPages)
                            .boxed()
                            .collect(Collectors.toList()));
        }
        return searchValues;
    }

    @Override
    @Transactional
    public void update(Long id, EmployeeDto employeeDto) {
        employeeValidatorService.validate(employeeDto);
        Employee employee = findEmployeeById(id);
        updateFields(employee, employeeDto);
        employeeRepository.save(employee);
    }

    private List<EmployeeDto> createDtoList(List<Employee> createdEmployees) {
        return createdEmployees.stream()
                .map(mapper::employeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    private Employee createEmployee(EmployeeDto employeeDto) {
        Employee createdEmployee = mapper.employeeDtoToEmployee(employeeDto);
        Position position = findEmployeePosition(employeeDto);
        createdEmployee.setPosition(position);
        return createdEmployee;
    }

    private List<Employee> createEmployeeList(List<EmployeeDto> validateddtolist) {
        return validateddtolist.stream()
                .map(this::createEmployee)
                .collect(Collectors.toList());
    }

    private Employee findEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageService.getMessage(EMPLOYEE_NOT_FOUND)));
    }

    private Position findEmployeePosition(EmployeeDto employeeDto) {
        Position position = positionService.findByTitleAndDepartmentTitle(employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle());
        positionValidatorService.validate(position, employeeDto);
        return position;
    }

    private Page<EmployeeDto> getEmployeeDtoPage(EmployeeSearchValues searchValues) {
        Page<Employee> employeePage = getEmployeePage(searchValues);
        return employeePage.map(mapper::employeeToEmployeeDto);
    }

    private Page<Employee> getEmployeePage(EmployeeSearchValues employeeSearchValues) {
        Pageable pageRequest = getPageRequest(employeeSearchValues);
        return employeeRepository.findAll(pageRequest);
    }

    private PageRequest getPageRequest(EmployeeSearchValues employeeSearchValues) {
        return PageRequest.of(
                employeeSearchValues.getPageNum() - 1,
                employeeSearchValues.getPageSize(),
                Sort.by(employeeSearchValues.getSortDirection(), employeeSearchValues.getSortField()));
    }

    private void updateFields(Employee employee, EmployeeDto dto) throws CustomValidationException {
        Position position = findEmployeePosition(dto);
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setSalary(dto.getSalary());
        employee.setPosition(position);
    }

    private List<EmployeeDto> validateDtoList(List<EmployeeDto> employeeDtoList, EmployeeCreateResponse employeeCreateResponse) {
        List<String> errorMessageList = new ArrayList<>();
        List<EmployeeDto> validDtoList = new ArrayList<>();
        employeeDtoList.forEach(dto -> {
            try {
                employeeValidatorService.validate(dto);
                validDtoList.add(dto);
            } catch (CustomValidationException | NotFoundException e) {
                errorMessageList.add(e.getMessage());
            }
        });
        employeeCreateResponse.setErrors(errorMessageList);
        return validDtoList;
    }
}
