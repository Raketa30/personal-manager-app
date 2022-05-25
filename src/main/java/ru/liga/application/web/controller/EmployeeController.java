package ru.liga.application.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.liga.application.api.EmployeeService;
import ru.liga.application.domain.dto.EmployeeDto;
import ru.liga.application.domain.dto.PageDto;
import ru.liga.application.domain.response.MultiCreateResponse;
import ru.liga.application.domain.response.SingleCreateResponse;

import java.util.List;

import static ru.liga.application.web.controller.EmployeeController.EMPLOYEES_URL;

@Slf4j
@RequiredArgsConstructor
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = EMPLOYEES_URL)
public class EmployeeController {
    public static final String EMPLOYEES_URL = "api/v1/employees";
    private final EmployeeService employeeService;
    //todo чо то логов не вижу и в сервисах тоже)
    // done
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleCreateResponse<EmployeeDto>> create(@RequestBody EmployeeDto employeeDto) {
        log.debug("EmployeeController create() employeeDto: {}", employeeDto);
        return ResponseEntity.ok(employeeService.create(employeeDto));
    }

    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MultiCreateResponse<EmployeeDto>> createMulti(@RequestBody List<EmployeeDto> employeeDtoList) {
        log.debug("EmployeeController createMulti() employeeDtoList: {}", employeeDtoList);
        return ResponseEntity.ok(employeeService.createAll(employeeDtoList));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String uuid) {
        log.debug("EmployeeController delete() employee uuid: {}", uuid);
        employeeService.delete(uuid);
    }

    @GetMapping(value = "/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getById(@PathVariable String uuid) {
        log.debug("EmployeeController getById() employee uuid: {}", uuid);
        return ResponseEntity.ok(employeeService.findByUuid(uuid));
    }

    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDto<EmployeeDto>> getPageList(@RequestBody PageDto<EmployeeDto> pageDto) {
        log.debug("EmployeeController getPageList() pageDto: {}", pageDto);
        return ResponseEntity.ok(employeeService.getPageList(pageDto));
    }

    @GetMapping(value = "/{uuid}/pdf",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf(@PathVariable String uuid) {
        log.debug("EmployeeController getPdf() employee uuid: {}", uuid);
        return ResponseEntity.ok(employeeService.getPdf(uuid));
    }

    @PutMapping(value = "/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody EmployeeDto employeeDto, @PathVariable String uuid) {
        log.debug("EmployeeController update() employee uuid: {}, update: {}", uuid, employeeDto);
        employeeService.update(uuid, employeeDto);
    }

}
