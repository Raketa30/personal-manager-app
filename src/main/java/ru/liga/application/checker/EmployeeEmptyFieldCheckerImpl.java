package ru.liga.application.checker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.application.api.EmployeeChecker;
import ru.liga.application.api.MessageService;
import ru.liga.application.domain.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.List;

import static ru.liga.application.domain.type.Message.*;

@Component
@RequiredArgsConstructor
//todo EmployeeCheckerImpl и интерфейс соответственно можно без Dto написать))
// done
public class EmployeeEmptyFieldCheckerImpl implements EmployeeChecker {
    private final MessageService messageService;

    @Override
    public List<String> check(EmployeeDto employeeDto) {
        List<String> messages = new ArrayList<>();
        if (employeeDto.getFirstname().isEmpty()) {
            messages.add(messageService.getMessage(EMPTY_FIRSTNAME));
        }
        if (employeeDto.getLastname().isEmpty()) {
            messages.add(messageService.getMessage(EMPTY_LASTNAME));
        }
        if (employeeDto.getPositionTitle().isEmpty()) {
            messages.add(messageService.getMessage(EMPTY_POSITION));
        }
        if (employeeDto.getDepartmentTitle().isEmpty()) {
            messages.add(messageService.getMessage(EMPTY_DEPARTMENT));
        }
        return messages;
    }
}
