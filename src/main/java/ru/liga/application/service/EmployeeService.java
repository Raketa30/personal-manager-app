package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.domain.entity.EmployeePosition;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.mapper.EmployeeMapper;
import ru.liga.application.repository.EmployeeRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.liga.application.common.Message.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeePositionService positionService;
    private final EmployeeMapper mapper;

    public Collection<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(mapper.getFunctionDtoToEntity())
                .collect(Collectors.toList());
    }

    @Transactional
    public String save(EmployeeDto employeeDto) {
        Optional<EmployeePosition> positionOptional =
                positionService.findByTitleAndDepartmentTitle(
                        employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle()
                );
        if (positionOptional.isEmpty()) {
            return POSITION_NOT_FOUND;
        }

        EmployeePosition employeePosition = positionOptional.get();
        int salary = employeeDto.getSalary();
        if (checkPositionSalary(employeePosition, salary)) {
            return SALARY_NOT_IN_POSITION_RANGE;
        }
        employeeRepository.save(mapper.build(employeeDto, employeePosition));
        return USER_CREATED_SUCCESSFULLY;

    }

    @Transactional
    public String update(EmployeeDto employeeDto) {
        Optional<EmployeePosition> positionOptional =
                positionService.findByTitleAndDepartmentTitle(
                        employeeDto.getDepartmentTitle(), employeeDto.getPositionTitle()
                );
        if (positionOptional.isEmpty()) {
            return POSITION_NOT_FOUND;
        }

        EmployeePosition employeePosition = positionOptional.get();
        int salary = employeeDto.getSalary();
        if (checkPositionSalary(employeePosition, salary)) {
            return SALARY_NOT_IN_POSITION_RANGE;
        }
        employeeRepository.save(mapper.update(employeeDto, employeePosition));
        return USER_UPDATED_SUCCESSFULLY;
    }

    private boolean checkPositionSalary(EmployeePosition employeePosition, int salary) {
        return salary < employeePosition.getMinSalary()
                || salary > employeePosition.getMaxSalary();
    }

}
