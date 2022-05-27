package ru.liga.application.shedule;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import ru.liga.application.api.EmployeeService;

@Configuration
@RequiredArgsConstructor
public class EmployeeScheduler {
    private final EmployeeService employeeService;

    @Scheduled(cron = "${scheduler.one_minute.cron}")
    public void deleteLastRow() {
        employeeService.deleteLastRow();
    }
}
