package ru.liga.application.support;

import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.liga.application.domain.entity.Employee;

public class EmployeeDeserializer extends JsonDeserializer<Employee> {
}
