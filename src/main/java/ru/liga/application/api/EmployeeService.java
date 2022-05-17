package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.search.EmployeeSearchValues;
import ru.liga.application.exception.CustomValidationException;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto) throws CustomValidationException;

    void delete(long employeeId);

    void deleteLastRow();

    EmployeeDto findById(long employeeId);

    Employee findEntityById(long employeeId);

    EmployeeSearchValues getPageList(EmployeeSearchValues employeeSearchValues);

    void update(Long id, EmployeeDto employeeDto) throws CustomValidationException;

}
