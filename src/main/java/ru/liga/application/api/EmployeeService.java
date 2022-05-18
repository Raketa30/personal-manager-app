package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.dto.EmployeePageDto;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.web.response.MultiCreateResponse;
import ru.liga.application.web.response.SingleCreateResponse;

import java.util.List;

public interface EmployeeService {
    SingleCreateResponse<EmployeeDto> create(EmployeeDto dto);

    MultiCreateResponse<EmployeeDto> createAll(List<EmployeeDto> dtoList);

    void delete(String uuid);

    void deleteLastRow();

    Employee findEntityByUuid(String uuid);

    EmployeeDto findByUuid(String uuid);

    EmployeePageDto getPageList(EmployeePageDto pageDto);

    void update(String uuid, EmployeeDto dto);

}
