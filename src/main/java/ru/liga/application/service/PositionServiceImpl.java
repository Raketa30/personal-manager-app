package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.api.PositionService;
import ru.liga.application.domain.entity.Position;
import ru.liga.application.repository.EmployeePositionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final EmployeePositionRepository positionRepository;

    @Override
    public Optional<Position> findByTitleAndDepartmentTitle(String positionTitle, String departmentTitle) {
        return positionRepository.findByTitleAndDepartmentTitle(positionTitle, departmentTitle);
    }
}
