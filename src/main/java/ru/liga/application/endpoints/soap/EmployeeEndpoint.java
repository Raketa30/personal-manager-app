package ru.liga.application.endpoints.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.liga.application.api.EmployeeService;
import ru.liga.application.domain.soap.employee.*;

import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://liga.ru/application/domain/soap/employee";

    private final EmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse add(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        EmployeeDto employeeDto = employeeService.save(request.getEmployeeDto());
        response.setEmployeeDto(employeeDto);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getById(@RequestPayload GetEmployeeByIdRequest request) {
        GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
        EmployeeDto employeeDto = employeeService.findById(request.getEmployeeId());
        response.setEmployeeDto(employeeDto);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse update(@RequestPayload UpdateEmployeeRequest request) {
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        EmployeeDto employeeDto = employeeService.update(request.getEmployeeDto());
        response.setEmployeeDto(employeeDto);
        return response;
    }
}
