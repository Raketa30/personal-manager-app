package ru.liga.application.endpoints.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.liga.application.domain.soap.employee.*;
import ru.liga.application.service.EmployeeService;
import ru.liga.application.service.validator.EmployeeValidationService;

import java.util.List;

import static ru.liga.application.common.Message.REQUEST_NOT_VALID;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://liga.ru/application/domain/soap/employee";

    private final EmployeeService employeeService;
    private final EmployeeValidationService validationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addNewEmployee(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        EmployeeDto employeeDto = request.getEmployeeDto();
        String responseMessage;
        if (validationService.validateRegistration(employeeDto)) {
            responseMessage = employeeService.save(employeeDto);
        } else {
            responseMessage = REQUEST_NOT_VALID;
        }
        response.setResponseMessage(responseMessage);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeListRequest")
    @ResponsePayload
    public GetEmployeeListResponse getAllEmployeeList(@RequestPayload GetEmployeeListRequest request) {
        GetEmployeeListResponse response = new GetEmployeeListResponse();
        List<EmployeeDto> employeeDtoList = response.getEmployeeDtoList();
        employeeDtoList.addAll(employeeService.findAll());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        EmployeeDto employeeDto = request.getEmployeeDto();
        String responseMessage;
        if (validationService.validateUpdate(employeeDto)) {
            responseMessage = employeeService.update(employeeDto);
        } else {
            responseMessage = REQUEST_NOT_VALID;
        }
        response.setResponseMessage(responseMessage);
        return response;
    }

}
