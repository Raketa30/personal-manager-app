package ru.liga.application.api;


import ru.liga.application.domain.dto.EmployeeDto;

import java.util.List;

public interface EmployeeChecker {
    List<String> check(EmployeeDto dto);
}
