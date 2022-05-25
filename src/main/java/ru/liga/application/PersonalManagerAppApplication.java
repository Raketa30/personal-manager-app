package ru.liga.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.liga.application.config.SwaggerConfig;

@SpringBootApplication
//@EnableScheduling //todo коммент
@Import(SwaggerConfig.class)
public class PersonalManagerAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonalManagerAppApplication.class, args);
    }
}
