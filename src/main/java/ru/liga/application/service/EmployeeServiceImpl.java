package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.EmployeePosition;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.mapper.EmployeeMapper;
import ru.liga.application.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.liga.application.common.Message.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeePositionService positionService;
    private final EmployeeMapper mapper;

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
                .map(mapper.getFunctionDtoFromEntity())
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(long id) {
        return employeeRepository.findById(id)
                .map(mapper.getFunctionDtoFromEntity())
                .orElseThrow(() -> new IllegalArgumentException(String.format(USER_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public String save(EmployeeDto employeeDto) {
        Optional<EmployeePosition> positionOptional =
                positionService.findByTitleAndDepartmentTitle(
                        employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle()
                );
        Employee createdEmployee = mapper.build(employeeDto, positionOptional
                .orElseThrow(() -> new IllegalArgumentException(POSITION_NOT_FOUND)));
        employeeRepository.save(createdEmployee);
        return USER_CREATED_SUCCESSFULLY;
    }

    @Override
    @Transactional
    public String update(EmployeeDto employeeDto) {
        Optional<EmployeePosition> positionOptional =
                positionService.findByTitleAndDepartmentTitle(
                        employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle()
                );
        Employee updatedEmployee = mapper.update(employeeDto, positionOptional
                .orElseThrow(() -> new IllegalArgumentException(POSITION_NOT_FOUND)));
        employeeRepository.save(updatedEmployee);
        return USER_UPDATED_SUCCESSFULLY;
    }


}
