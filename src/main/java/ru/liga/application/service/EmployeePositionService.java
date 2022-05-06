package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.domain.entity.EmployeePosition;
import ru.liga.application.repository.EmployeePositionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeePositionService {
    private final EmployeePositionRepository positionRepository;

    public Optional<EmployeePosition> findByTitleAndDepartmentTitle(String positionTitle,
                                                                    String departmentTitle) {
        return positionRepository.findByTitleAndDepartmentTitle(positionTitle, departmentTitle);
    }
}
