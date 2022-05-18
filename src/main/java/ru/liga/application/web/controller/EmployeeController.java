package ru.liga.application.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.application.api.EmployeeService;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.dto.EmployeePageDto;
import ru.liga.application.web.response.AbstractResponse;

import java.util.List;

import static ru.liga.application.web.controller.EmployeeController.EMPLOYEES_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = EMPLOYEES_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    public static final String EMPLOYEES_URL = "api/v1/employees";
    private final EmployeeService employeeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractResponse> create(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.create(employeeDto));
    }

    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractResponse> createMulti(@RequestBody List<EmployeeDto> employeeDtoList) {
        return ResponseEntity.ok(employeeService.createAll(employeeDtoList));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String uuid) {
        employeeService.delete(uuid);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable String uuid) {
        return ResponseEntity.ok(employeeService.findByUuid(uuid));
    }

    @GetMapping("/list")
    public ResponseEntity<EmployeePageDto> getPageList(@RequestBody EmployeePageDto searchValues) {
        return ResponseEntity.ok(employeeService.getPageList(searchValues));
    }

    @PutMapping(value = "/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody EmployeeDto employeeDto, @PathVariable String uuid) {
        employeeService.update(uuid, employeeDto);
    }
}
