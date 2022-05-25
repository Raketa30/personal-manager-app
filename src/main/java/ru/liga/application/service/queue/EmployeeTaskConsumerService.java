package ru.liga.application.service.queue;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.application.domain.entity.Employee;
import ru.liga.application.repository.EmployeeRepository;
import ru.liga.application.repository.KafkaTaskRepository;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeTaskConsumerService {
    private static final int ONE_TABLE_ROW = 1;
    private final EmployeeRepository employeeRepository;
    private final KafkaTaskRepository taskRepository;

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        log.info("EmployeeTaskConsumerService save saved: {}", employee);
    }

    @Transactional
    @KafkaListener(topics = "${operation.task.topic.employee}")
    public void saveTaskListener(ConsumerRecord<String, Employee> consumerRecord) {
        Employee employee = consumerRecord.value();
        String taskUuid = consumerRecord.key();
        if (isDeletedTask(taskUuid)) {
            saveEmployee(employee);
            log.info("EmployeeTaskConsumerService saveTaskListener() task {} saved", taskUuid);
        }
    }

    private boolean isDeletedTask(String taskUuid) {
        int deletedRows = taskRepository.deleteByUuid(taskUuid);
        //todo что за цифра ?)))
        // done
        return deletedRows == ONE_TABLE_ROW;
    }
}
