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
import ru.liga.application.domain.dto.EmployeePageDto;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.Position;
import ru.liga.application.exception.NotFoundException;
import ru.liga.application.mapper.EmployeeMapper;
import ru.liga.application.repository.EmployeeRepository;
import ru.liga.application.web.response.MultiCreateResponse;
import ru.liga.application.web.response.SingleCreateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public SingleCreateResponse<EmployeeDto> create(EmployeeDto employeeDto) {
        SingleCreateResponse<EmployeeDto> response = new SingleCreateResponse<>();
        List<String> errors = validateEmployeeDto(employeeDto);
        if (!errors.isEmpty()) {
            response.setErrors(errors);
            return response;
        }
        Employee createdEmployee = createEmployee(employeeDto);
        Employee employee = employeeRepository.save(createdEmployee);
        response.setDto(mapper.employeeToEmployeeDto(employee));
        return response;
    }

    @Override
    @Transactional
    public MultiCreateResponse<EmployeeDto> createAll(List<EmployeeDto> employeeDtoList) {
        MultiCreateResponse<EmployeeDto> response = new MultiCreateResponse<>();
        List<EmployeeDto> validDtoList = validateDtoList(employeeDtoList, response);
        List<Employee> createdEmployeeList = createEmployeeList(validDtoList);
        List<Employee> savedEmployeeList = employeeRepository.saveAll(createdEmployeeList);
        List<EmployeeDto> savedEmployeeDtoList = createDtoList(savedEmployeeList);
        response.setCreated(savedEmployeeDtoList);
        return response;
    }

    @Override
    @Transactional
    public void delete(String uuid) {
        employeeRepository.deleteEmployeeByUuid(uuid);
    }

    @Override
    @Transactional
    public void deleteLastRow() {
        employeeRepository.deleteLastRow();
    }

    @Override
    public Employee findEntityByUuid(String employeeUuid) {
        return employeeRepository.findEmployeeByUuid(employeeUuid)
                .orElseThrow(() -> {
                    String message = messageService.getMessage(EMPLOYEE_NOT_FOUND);
                    return new NotFoundException(String.format(message, employeeUuid));
                });
    }

    @Override
    public EmployeeDto findByUuid(String employeeUuid) {
        return employeeRepository.findEmployeeByUuid(employeeUuid)
                .map(mapper::employeeToEmployeeDto)
                .orElseThrow(() -> {
                    String message = messageService.getMessage(EMPLOYEE_NOT_FOUND);
                    return new NotFoundException(String.format(message, employeeUuid));
                });
    }

    @Override
    public EmployeePageDto getPageList(EmployeePageDto searchValues) {
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
    public void update(String uuid, EmployeeDto employeeDto) {
        validateEmployeeDto(employeeDto);
        Employee employee = findEmployeeById(uuid);
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
        Optional<Position> position =
                positionService.findByTitleAndDepartmentTitle(employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle());
        createdEmployee.setPosition(position.orElseThrow());
        return createdEmployee;
    }

    private List<Employee> createEmployeeList(List<EmployeeDto> validatedDtoList) {
        return validatedDtoList.stream()
                .map(this::createEmployee)
                .collect(Collectors.toList());
    }

    private Employee findEmployeeById(String uuid) {
        return employeeRepository.findEmployeeByUuid(uuid)
                .orElseThrow(() -> new NotFoundException(messageService.getMessage(EMPLOYEE_NOT_FOUND)));
    }

    private Optional<Position> findEmployeePosition(EmployeeDto employeeDto) {
        return positionService.findByTitleAndDepartmentTitle(employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle());
    }

    private Page<EmployeeDto> getEmployeeDtoPage(EmployeePageDto searchValues) {
        Page<Employee> employeePage = getEmployeePage(searchValues);
        return employeePage.map(mapper::employeeToEmployeeDto);
    }

    private Page<Employee> getEmployeePage(EmployeePageDto employeePageDto) {
        Pageable pageRequest = getPageRequest(employeePageDto);
        return employeeRepository.findAll(pageRequest);
    }

    private PageRequest getPageRequest(EmployeePageDto employeePageDto) {
        int zeroBasedPageNumber = employeePageDto.getPageNum() - 1;
        int pageSize = employeePageDto.getPageSize();
        Sort.Direction sortDirection = employeePageDto.getSortDirection();
        String sortField = employeePageDto.getSortField();
        Sort sortBy = Sort.by(sortDirection, sortField);
        //todo волшебное значение)) Вынести в константу и дать названия в соответствии для чего это
        // done
        return PageRequest.of(zeroBasedPageNumber, pageSize, sortBy);
    }

    private void updateFields(Employee employee, EmployeeDto dto) {
        Position position = findEmployeePosition(dto).orElseThrow();
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setSalary(dto.getSalary());
        employee.setPosition(position);
    }

    private List<EmployeeDto> validateDtoList(List<EmployeeDto> employeeDtoList, MultiCreateResponse<EmployeeDto> multiCreateResponse) {
        employeeDtoList.removeIf(dto -> {
            List<String> errors = validateEmployeeDto(dto);
            if (!errors.isEmpty()) {
                multiCreateResponse.putNotValidDtoWithErrorList(dto, errors);
                return true;
            }
            return false;
        });
        return employeeDtoList;
    }

    private List<String> validateEmployeeDto(EmployeeDto employeeDto) {
        List<String> errors = new ArrayList<>();
        errors.addAll(employeeValidatorService.validate(employeeDto));
        errors.addAll(positionValidatorService.validate(employeeDto));
        return errors;
    }
}
