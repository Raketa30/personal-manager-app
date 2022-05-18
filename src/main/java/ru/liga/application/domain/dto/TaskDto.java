package ru.liga.application.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TaskDto {
    private String employeeUuid;
    private String uuid;
    private boolean completed;
    private String description;
}
