package ru.liga.application.support;

import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.liga.application.domain.entity.Employee;

//todo перенеси в пакет support
// done
public class EmployeeDeserializer extends JsonDeserializer<Employee> {
}
