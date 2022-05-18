package ru.liga.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.liga.application.domain.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    void deleteByUuid(String uuid);

    Task findTaskByUuid(String uuid);
}
