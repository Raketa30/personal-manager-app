package ru.liga.application.service.validator;

import org.springframework.stereotype.Service;
import ru.liga.application.domain.soap.employee.EmployeeDto;

import java.util.Objects;

@Service
public class EmployeeValidationService {
    public boolean validateRegistration(EmployeeDto employeeDto) {
        return employeeDto.getId() == 0 &&
                Objects.nonNull(employeeDto.getFirstname()) &&
                Objects.nonNull(employeeDto.getLastname()) &&
                Objects.nonNull(employeeDto.getPositionTitle()) &&
                Objects.nonNull(employeeDto.getDepartmentTitle());
    }

    public boolean validateUpdate(EmployeeDto employeeDto) {
        return employeeDto.getId() != 0 &&
                Objects.nonNull(employeeDto.getFirstname()) &&
                Objects.nonNull(employeeDto.getLastname()) &&
                Objects.nonNull(employeeDto.getPositionTitle()) &&
                Objects.nonNull(employeeDto.getDepartmentTitle());
    }

}
