package ru.liga.application.api;

import ru.liga.application.domain.entity.Position;

public interface PositionService {
    Position findByTitleAndDepartmentTitle(String positionTitle, String departmentTitle);
}
