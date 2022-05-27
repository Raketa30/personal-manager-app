package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.application.api.EmployeeChecker;
import ru.liga.application.api.EmployeeValidatorService;
import ru.liga.application.domain.dto.EmployeeDto;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeValidatorServiceImpl implements EmployeeValidatorService {
    private final EmployeeChecker dtoChecker;

    @Override
    public List<String> validate(EmployeeDto dto) {
        List<String> checkerMessages = dtoChecker.check(dto);
        if (!checkerMessages.isEmpty()) {
            log.debug("EmployeeValidatorServiceImpl validate() dto: {}, messages: {}", dto, checkerMessages);
            return checkerMessages;
        }
        return Collections.emptyList();
    }
}
