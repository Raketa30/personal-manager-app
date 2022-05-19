package ru.liga.application.service.queue;

import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.liga.application.domain.entity.Employee;

public class EmployeeDeserializer extends JsonDeserializer<Employee> { //todo перенеси в пакет support
}
