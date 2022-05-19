package ru.liga.application.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "position", schema = "employee_management")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "${entity.validation.position.title}")
    private String title;

    @Column(name = "min_salary")
    private Integer minSalary;

    @Column(name = "max_salary")
    private Integer maxSalary;

    @Column(name = "max_task_size")
    private Integer maxTaskSize;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "position")
    private Set<Employee> employees = new HashSet<>();

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Position that = (Position) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "title = " + title + ", " +
                "department = " + department + ")";
    }
}
