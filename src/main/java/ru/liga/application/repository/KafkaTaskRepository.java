package ru.liga.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.liga.application.domain.entity.KafkaTask;

@Repository
public interface KafkaTaskRepository extends JpaRepository<KafkaTask, Long> {
    @Modifying
    int deleteByUuid(String uuid);
}
