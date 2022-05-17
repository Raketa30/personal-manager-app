package ru.liga.application.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.application.domain.dto.EmployeeDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeCreateResponse { //todo должен лежать в другом пакете
    private List<EmployeeDto> employeeDtoList;
    private List<String> errors;
    private String createResultMessage;
}
