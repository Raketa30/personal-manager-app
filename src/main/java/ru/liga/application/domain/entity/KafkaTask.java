package ru.liga.application.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "kafka_task", schema = "employee_management")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class KafkaTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;
}
