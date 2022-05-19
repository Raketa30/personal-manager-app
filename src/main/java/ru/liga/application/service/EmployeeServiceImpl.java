package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.api.*;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.dto.PageDto;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.Position;
import ru.liga.application.domain.response.MultiCreateResponse;
import ru.liga.application.domain.response.SingleCreateResponse;
import ru.liga.application.exception.NotFoundException;
import ru.liga.application.mapper.EmployeeMapper;
import ru.liga.application.repository.EmployeeRepository;
import ru.liga.application.service.queue.EmployeeTaskProducerService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.liga.application.domain.type.Message.EMPLOYEE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionService positionService;
    private final MessageService messageService;
    private final EmployeeValidatorService employeeValidatorService;
    private final PositionValidatorService positionValidatorService;
    private final EmployeeTaskProducerService producerService;

    @Override
    @Transactional
    public SingleCreateResponse<EmployeeDto> create(EmployeeDto employeeDto) {
        SingleCreateResponse<EmployeeDto> response = new SingleCreateResponse<>();
        List<String> errors = validateEmployeeDto(employeeDto);
        if (!errors.isEmpty()) {
            response.setDto(employeeDto);
            response.setMessages(errors);
            return response;
        }
        Employee createdEmployee = createEmployee(employeeDto);
        producerService.createTask(Collections.singletonList(createdEmployee));
        response.setMessages(Collections.singletonList("created"));
        return response;
    }

    @Override
    public MultiCreateResponse<EmployeeDto> createAll(List<EmployeeDto> employeeDtoList) {
        MultiCreateResponse<EmployeeDto> response = new MultiCreateResponse<>();
        List<EmployeeDto> validDtoList = validateDtoList(employeeDtoList, response);
        List<Employee> createdEmployeeList = createEmployeeList(validDtoList);
        producerService.createTask(createdEmployeeList);
        return response;
    }

    @Override
    @Transactional
    public void delete(String uuid) {
        employeeRepository.deleteEmployeeByUuid(uuid);
        log.info("EmployeeService delete() deleted employee with uuid: {}", uuid);
    }

    @Override
    @Transactional
    public void deleteLastRow() {
        employeeRepository.deleteLastRow();
    }

    @Override
    public Employee findEntityByUuid(String uuid) {
        return employeeRepository.findEmployeeByUuid(uuid)
                .orElseThrow(() -> {
                    String message = messageService.getMessage(EMPLOYEE_NOT_FOUND);
                    log.info("EmployeeService findEntityByUuid() employee with uuid not found: {}", uuid);
                    return new NotFoundException(String.format(message, uuid));
                });
    }

    @Override
    public EmployeeDto findByUuid(String uuid) {
        return employeeRepository.findEmployeeByUuid(uuid)
                .map(EmployeeMapper::employeeToEmployeeDto)
                .orElseThrow(() -> {
                    String message = messageService.getMessage(EMPLOYEE_NOT_FOUND);
                    log.info("EmployeeService findByUuid() employee with uuid not found: {}", uuid);
                    return new NotFoundException(String.format(message, uuid));
                });
    }

    @Override
    public PageDto<EmployeeDto> getPageList(PageDto<EmployeeDto> pageDto) {
        Page<EmployeeDto> page = getPageWithEmployeeDto(pageDto);
        pageDto.setPage(page);
        setPageNumbersToPageDto(pageDto);
        return pageDto;
    }

    @Override
    @Transactional
    public void update(String uuid, EmployeeDto employeeDto) {
        validateEmployeeDto(employeeDto);
        Employee employee = findEmployeeById(uuid);
        updateFields(employee, employeeDto);
        producerService.createTask(Collections.singletonList(employee));
        log.info("EmployeeService update() saved employee: {}", employee);
    }

    private Employee createEmployee(EmployeeDto employeeDto) {
        Employee createdEmployee = EmployeeMapper.employeeDtoToEmployee(employeeDto);
        Optional<Position> position =
                positionService.findByTitleAndDepartmentTitle(employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle());
        createdEmployee.setPosition(position.orElseThrow());
        createdEmployee.setUuid(UUID.randomUUID().toString());
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

    private PageRequest getPageRequest(PageDto<EmployeeDto> pageDto) {
        int zeroBasedPageNumber = pageDto.getPageNum() - 1;
        int pageSize = pageDto.getPageSize();
        Sort.Direction sortDirection = pageDto.getSortDirection();
        String sortField = pageDto.getSortField();
        Sort sortBy = Sort.by(sortDirection, sortField);
        return PageRequest.of(zeroBasedPageNumber, pageSize, sortBy);
    }

    private Page<EmployeeDto> getPageWithEmployeeDto(PageDto<EmployeeDto> pageDto) {
        Pageable pageRequest = getPageRequest(pageDto);
        Page<Employee> employeePage = employeeRepository.findAll(pageRequest);
        return employeePage.map(EmployeeMapper::employeeToEmployeeDto);
    }

    private void setPageNumbersToPageDto(PageDto<EmployeeDto> pageDto) {
        Page<EmployeeDto> page = pageDto.getPage();
        //todo волшебная цифра)) вынеси в константу
        // done
        if (page.hasContent()) {
            //todo вынести в константу ?
            // done - нигде больше не используется же
            final int firstPage = 1;
            int totalPages = page.getTotalPages();
            pageDto.setPageNumbers(
                    IntStream.rangeClosed(firstPage, totalPages)
                            .boxed()
                            .collect(Collectors.toList()));
        }
    }

    private void updateFields(Employee employee, EmployeeDto dto) {
        Position position = findEmployeePosition(dto).orElseThrow();
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setSalary(dto.getSalary());
        employee.setPosition(position);
    }

    private List<EmployeeDto> validateDtoList(List<EmployeeDto> employeeDtoList, MultiCreateResponse<EmployeeDto> multiCreateResponse) {
        Map<EmployeeDto, List<String>> validationErrorMap = new HashMap<>();
        employeeDtoList.removeIf(dto -> {
            List<String> errors = validateEmployeeDto(dto);
            if (!errors.isEmpty()) {
                validationErrorMap.put(dto, errors);
                return true;
            }
            return false;
        });
        multiCreateResponse.setDtoErrorMap(validationErrorMap);
        return employeeDtoList;
    }

    private List<String> validateEmployeeDto(EmployeeDto employeeDto) {
        List<String> errors = new ArrayList<>();
        errors.addAll(employeeValidatorService.validate(employeeDto));
        errors.addAll(positionValidatorService.validate(employeeDto));
        return errors;
    }
}
