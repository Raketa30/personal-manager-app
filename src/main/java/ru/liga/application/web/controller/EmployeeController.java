package ru.liga.application.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.liga.application.api.EmployeeService;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.search.EmployeeSearchValues;
import ru.liga.application.exception.CustomValidationException;

import java.net.URI;

import static ru.liga.application.web.RestUrlV1.EMPLOYEES_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = EMPLOYEES_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) throws CustomValidationException {
        EmployeeDto created = employeeService.create(employeeDto);
        URI uriOfNewResource = getUriOfNewResource(created);
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<EmployeeSearchValues> getPageList(EmployeeSearchValues searchValues) {
        return ResponseEntity.ok(employeeService.getPageList(searchValues));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) throws CustomValidationException {
        employeeService.update(id, employeeDto);
    }

    private URI getUriOfNewResource(EmployeeDto created) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(EMPLOYEES_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
    }
}
