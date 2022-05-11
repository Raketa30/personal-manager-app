package ru.liga.application.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.domain.entity.EmployeePosition;
import ru.liga.application.domain.soap.employee.EmployeeDto;
import ru.liga.application.service.EmployeePositionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.liga.application.common.Message.*;

@Service
@RequiredArgsConstructor
public class EmployeeValidatorService { //todo добавь интерфейс и обращайся через него
    private final EmployeePositionService positionService;

    public List<String> validateRegistration(EmployeeDto employeeDto) {
        List<String> messages = new ArrayList<>();
        if (employeeDto.getId() != 0) { //todo волшебное значение. Вынести в константу + NPE не вылетит ?
            messages.add(WRONG_ID_DURING_REGISTRATION);
        }
        validatePosition(employeeDto).ifPresent(messages::add);//todo попробуй сделать без Optional
        messages.addAll(checkDtoEmptyFields(employeeDto));
        return messages;

        //todo не оставляй комменты в коде. Такие комметы могут сбивать с толку
//        return employeeDto.getId() == 0 &&
//                !employeeDto.getFirstname().isEmpty() &&
//                !employeeDto.getLastname().isEmpty() &&
//                !employeeDto.getPositionTitle().isEmpty() &&
//                !employeeDto.getDepartmentTitle().isEmpty();
    }

    public List<String> validateUpdate(EmployeeDto employeeDto) {
        List<String> messages = new ArrayList<>();
        if (employeeDto.getId() == 0) {
            messages.add(WRONG_ID_DURING_UPDATE);
        if (employeeDto.getId() == 0) { //todo волшебное значение. Вынести в константу + NPE не вылетит ?
            messages.add(WRONG_ID_DURING_UPDATE);
        }
        validatePosition(employeeDto).ifPresent(messages::add); //todo попробуй сделать без Optional
        messages.addAll(checkDtoEmptyFields(employeeDto));
        return messages;
    }

    private List<String> checkDtoEmptyFields(EmployeeDto employeeDto) {
        List<String> messages = new ArrayList<>();
        if (employeeDto.getFirstname().isEmpty()) {
            messages.add(EMPTY_USERNAME);
        }
        if (employeeDto.getLastname().isEmpty()) {
            messages.add(EMPTY_LASTNAME);
        }
        if (employeeDto.getPositionTitle().isEmpty()) {
            messages.add(EMPTY_POSITION);
        }
        if (employeeDto.getDepartmentTitle().isEmpty()) {
            messages.add(EMPTY_DEPARTMENT);
        }
        return messages;
    }

    private boolean checkWrongPositionSalary(EmployeePosition employeePosition, int salary) {
        return salary < employeePosition.getMinSalary() //todo лишний перенос
                || salary > employeePosition.getMaxSalary();
    }

    private String getWrongSalaryMessage(EmployeePosition employeePosition, int salary) {
        return String.format(SALARY_NOT_IN_POSITION_RANGE,
                employeePosition.getTitle(),
                employeePosition.getMinSalary(),
                employeePosition.getMaxSalary(),
                salary
        );
    }

    private Optional<String> validatePosition(EmployeeDto employeeDto) {
        Optional<EmployeePosition> positionOptional =
                positionService.findByTitleAndDepartmentTitle(
                        employeeDto.getPositionTitle(), employeeDto.getDepartmentTitle()
                );
        if (positionOptional.isEmpty()) {
            return Optional.of(POSITION_NOT_FOUND);
        }
        EmployeePosition employeePosition = positionOptional.get();
        int salary = employeeDto.getSalary();
        if (checkWrongPositionSalary(employeePosition, salary)) {
            return Optional.of(getWrongSalaryMessage(employeePosition, salary));
        }
        return Optional.empty();
    }


}
