package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.search.EmployeeSearchValues;
import ru.liga.application.exception.CustomValidationException;
import ru.liga.application.web.EmployeeCreateResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto) throws CustomValidationException;

    EmployeeCreateResponse createAll(List<EmployeeDto> employeeDtoList);

    void delete(long employeeId);

    void deleteLastRow();

    EmployeeDto findById(long employeeId);

    Employee findEntityById(long employeeId);

    EmployeeSearchValues getPageList(EmployeeSearchValues employeeSearchValues);

    void update(Long id, EmployeeDto employeeDto) throws CustomValidationException;

}
