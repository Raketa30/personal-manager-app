package ru.liga.application.endpoints.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.liga.application.domain.soap.employee.*;
import ru.liga.application.service.EmployeeService;
import ru.liga.application.service.validation.EmployeeValidatorService;

import java.util.List;

import static ru.liga.application.common.Message.REQUEST_NOT_VALID;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://liga.ru/application/domain/soap/employee";

    private final EmployeeService employeeService;
    private final EmployeeValidatorService validationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse add(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        EmployeeDto employeeDto = request.getEmployeeDto();
        List<String> errorMessages = validationService.validateRegistration(employeeDto);
        if (errorMessages.isEmpty()) {
            response.getResponseMessage().add(employeeService.save(employeeDto));
        } else {
            response.getResponseMessage().addAll(errorMessages);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public void delete(@RequestPayload DeleteEmployeeRequest request) {
        employeeService.delete(request.getEmployeeId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeListRequest")
    @ResponsePayload
    public GetEmployeeListResponse getAll(@RequestPayload GetEmployeeListRequest request) {
        GetEmployeeListResponse response = new GetEmployeeListResponse();
        List<EmployeeDto> employeeDtoList = response.getEmployeeDtoList();
        employeeDtoList.addAll(employeeService.findAll());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse update(@RequestPayload UpdateEmployeeRequest request) {
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        EmployeeDto employeeDto = request.getEmployeeDto();
        List<String> errorMessages = validationService.validateUpdate(employeeDto);
        if (errorMessages.isEmpty()) {
            response.getResponseMessage().add(employeeService.update(employeeDto));
        } else {
            response.getResponseMessage().addAll(errorMessages);
        }
        return response;
    }

}
