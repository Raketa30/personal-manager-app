package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.api.EmployeeService;
import ru.liga.application.api.TaskService;
import ru.liga.application.api.TaskValidatorService;
import ru.liga.application.domain.dto.TaskDto;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.Task;
import ru.liga.application.mapper.TaskMapper;
import ru.liga.application.repository.TaskRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;
    private final TaskValidatorService taskValidatorService;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskDto create(TaskDto taskDto) {
        Task created = createTask(taskDto);
        Task save = taskRepository.save(created);
        log.info("TaskServiceImpl create() saved Task: {}", save);
        return taskMapper.taskToTaskDto(save);
    }

    @Override
    @Transactional
    public void delete(String uuid) {
        log.info("TaskServiceImpl delete() deleted Task with uuid: {}", uuid);
        taskRepository.deleteByUuid(uuid);
    }

    @Override
    public Task findByUuid(String uuid) {
        return taskRepository.findTaskByUuid(uuid);
    }

    @Override
    @Transactional
    public void update(String uuid, TaskDto taskDto) {
        taskValidatorService.validate(taskDto);
        Task updated = updateTaskFromDto(taskDto);
        Task save = taskRepository.save(updated);
        log.info("TaskServiceImpl update() saved Task: {}", save);
    }

    private Task createTask(TaskDto taskDto) {
        taskValidatorService.validate(taskDto);
        Employee employee = employeeService.findEntityByUuid(taskDto.getEmployeeUuid());
        Task created = new Task();
        created.setEmployee(employee);
        created.setDescription(taskDto.getDescription());
        created.setUuid(UUID.randomUUID().toString());
        return created;
    }

    private Task updateTaskFromDto(TaskDto taskDto) {
        Task task = taskRepository.findTaskByUuid(taskDto.getUuid());
        task.setCompleted(taskDto.isCompleted());
        task.setDescription(taskDto.getDescription());
        return task;
    }
}
