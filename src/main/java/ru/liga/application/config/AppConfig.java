package ru.liga.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.liga.application.mapper.EmployeeMapper;
import ru.liga.application.mapper.TaskMapper;

@Configuration
public class AppConfig {

    @Bean
    public EmployeeMapper employeeMapper() {
        return new EmployeeMapper(taskMapper());
    }

    @Bean
    public TaskMapper taskMapper() {
        return new TaskMapper();
    }
}
