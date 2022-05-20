package ru.liga.application.domain.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "firstname")
    @NotBlank(message = "${entity.validation.employee.firstname}")
    private String firstname;

    @Column(name = "lastname")
    @NotBlank(message = "${entity.validation.employee.lastname}")
    private String lastname;

    @Column(name = "salary")
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<Task> tasks = new HashSet<>();

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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + uuid + ", " +
                "firstname = " + firstname + ", " +
                "lastname = " + lastname + ", " +
                "salary = " + salary + ", " +
                "position = " + position + ")";
    }
}
