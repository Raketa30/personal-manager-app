package ru.liga.application.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeDto {
    private long id;
    private String firstname;
    private String lastname;
    private int salary;
    private String positionTitle;
    private String departmentTitle;
    private List<TaskDto> tasks = new ArrayList<>();
}
