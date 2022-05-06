package ru.liga.application.shedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.liga.application.service.EmployeeService;

@Component
@RequiredArgsConstructor
public class EmployeeDBScheduler {
    private final EmployeeService employeeService;

    @Scheduled(cron = "0/60 * * * * *")
    public void deleteLastRow() {
        employeeService.deleteLastRow();
    }
}
