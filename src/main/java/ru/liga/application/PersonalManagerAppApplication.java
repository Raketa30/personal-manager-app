package ru.liga.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.liga.application.config.SwaggerConfig;

@SpringBootApplication
@EnableScheduling
@Import(SwaggerConfig.class)
public class PersonalManagerAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonalManagerAppApplication.class, args);
    }
}
