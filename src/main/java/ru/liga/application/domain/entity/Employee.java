package ru.liga.application.domain.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "employee_management")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    @NotBlank(message = "Имя обязательно") //todo оставлять русские слова в коде плохая практика. Используй ResourceBundle. Можно посмотреть в fccr класс MessageService
    private String firstname;

    @Column(name = "lastname")
    @NotBlank(message = "Фамилия обязательно") //todo оставлять русские слова в коде плохая практика. Используй ResourceBundle. Можно посмотреть в fccr класс MessageService
    private String lastname;

    @Column(name = "salary")
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private EmployeePosition employeePosition;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return id != null && Objects.equals(id, employee.id);
    }
}
