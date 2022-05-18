package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.dto.EmployeePageDto;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.exception.CustomValidationException;
import ru.liga.application.web.response.AbstractResponse;

import java.util.List;

public interface EmployeeService {
    AbstractResponse create(EmployeeDto dto) throws CustomValidationException;

    AbstractResponse createAll(List<EmployeeDto> dtoList);

    void delete(String uuid);

    void deleteLastRow();

    Employee findEntityByUuid(String uuid);

    EmployeeDto findByUuid(String uuid);

    EmployeePageDto getPageList(EmployeePageDto pageDto);

    void update(String uuid, EmployeeDto dto) throws CustomValidationException;

}
