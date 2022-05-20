package ru.liga.application.service.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.domain.entity.KafkaTask;
import ru.liga.application.repository.KafkaTaskRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeTaskProducerService {
    private final KafkaTemplate<String, Employee> kafkaTemplate;
    private final KafkaTaskRepository taskRepository;

    @Value("${operation.task.topic.employee}")
    private String topic;

    @Transactional
    public void createTask(List<Employee> employees) {
        employees.forEach(employee -> {
            String uuid = UUID.randomUUID().toString();
            KafkaTask task = new KafkaTask();
            task.setUuid(uuid);
            taskRepository.save(task);
            kafkaTemplate.send(topic, uuid, employee);
        });
        kafkaTemplate.flush();
    }

}
