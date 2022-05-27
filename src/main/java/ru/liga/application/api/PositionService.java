package ru.liga.application.api;

import ru.liga.application.domain.entity.Position;

import java.util.Optional;

public interface PositionService {
    Optional<Position> findByTitleAndDepartmentTitle(String positionTitle, String departmentTitle);
}
