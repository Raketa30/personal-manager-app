package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.domain.entity.EmployeePosition;
import ru.liga.application.repository.EmployeePositionRepository;

import static ru.liga.application.common.Message.POSITION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmployeePositionService {
    private final EmployeePositionRepository positionRepository;
    private final MessageService messageService;

    public EmployeePosition findByTitleAndDepartmentTitle(String positionTitle, String departmentTitle) {
        return positionRepository.findByTitleAndDepartmentTitle(positionTitle, departmentTitle)
                .orElseThrow(() -> {
                    String message = messageService.getMessage(POSITION_NOT_FOUND);
                    return new IllegalArgumentException(message);
                });
    }
}
