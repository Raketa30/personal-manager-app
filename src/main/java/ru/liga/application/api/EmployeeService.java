package ru.liga.application.api;

import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.dto.PageDto;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.response.MultiCreateResponse;
import ru.liga.application.domain.response.SingleCreateResponse;

import java.util.List;

public interface EmployeeService {
    SingleCreateResponse<EmployeeDto> create(EmployeeDto dto);

    MultiCreateResponse<EmployeeDto> createAll(List<EmployeeDto> dtoList);

    void delete(String uuid);

    void deleteLastRow();

    Employee findEntityByUuid(String uuid);

    EmployeeDto findByUuid(String uuid);

    PageDto<EmployeeDto> getPageList(PageDto<EmployeeDto> pageDto);

    void update(String uuid, EmployeeDto dto);
}
