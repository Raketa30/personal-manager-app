package ru.liga.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.application.domain.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "delete from Employee e where e.id = (select max (ee.id) from Employee ee)")
    @Modifying
    void deleteLastRow();
}
