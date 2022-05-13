package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.liga.application.api.MessageService;
import ru.liga.application.api.PositionService;
import ru.liga.application.domain.entity.Position;
import ru.liga.application.exception.PositionNotFoundException;
import ru.liga.application.repository.EmployeePositionRepository;

import static ru.liga.application.domain.type.Message.POSITION_NOT_FOUND;

@Service
@RequiredArgsConstructor
//todo добавить интерфейс и использовать через него
// done
public class PositionServiceImpl implements PositionService {
    private final EmployeePositionRepository positionRepository;
    private final MessageService messageService;

    @Override
    @SneakyThrows
    public Position findByTitleAndDepartmentTitle(String positionTitle, String departmentTitle) {
        return positionRepository.findByTitleAndDepartmentTitle(positionTitle, departmentTitle)
                .orElseThrow(() -> {
                    String message = messageService.getMessage(POSITION_NOT_FOUND);
                    //todo сделай свой Exception + чтоб он был Checked
                    // done
                    return new PositionNotFoundException(message);
                });
    }
}
