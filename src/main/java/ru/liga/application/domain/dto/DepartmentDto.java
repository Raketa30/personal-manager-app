package ru.liga.application.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DepartmentDto {
    private long id;
    private String title;
    private List<String> positionTitles;
}
