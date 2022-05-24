package ru.liga.application.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.liga.application.api.TaskService;
import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Task;

import java.net.URI;

import static ru.liga.application.web.controller.TaskController.TASKS_URL;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = TASKS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {
    public static final String TASKS_URL = "api/v1/tasks";
    private final TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto taskDto) {
        TaskDto created = taskService.create(taskDto);
        URI uriOfNewResource = getUriOfNewResource(created);
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Task> findByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(taskService.findByUuid(uuid));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String uuid) {
        taskService.delete(uuid);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody TaskDto taskDto, @PathVariable String uuid) {
        taskService.update(uuid, taskDto);
    }

    private URI getUriOfNewResource(TaskDto created) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(TASKS_URL + "/{uuid}")
                .buildAndExpand(created.getUuid()).toUri();
    }
}
