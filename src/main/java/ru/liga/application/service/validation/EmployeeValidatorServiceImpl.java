package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.api.EmployeeDtoChecker;
import ru.liga.application.api.EmployeeValidatorService;
import ru.liga.application.domain.dto.EmployeeDto;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeValidatorServiceImpl implements EmployeeValidatorService {
    private final EmployeeDtoChecker dtoChecker;

    @Override
    public List<String> validate(EmployeeDto dto) {
        List<String> checkerMessages = dtoChecker.check(dto);
        if (!checkerMessages.isEmpty()) {
            return checkerMessages;
        }
        return Collections.emptyList();
    }
}
