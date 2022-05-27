package ru.liga.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.application.domain.entity.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "delete from Employee e where e.id = (select max (ee.id) from Employee ee)")
    @Modifying
    void deleteLastRow();

    @Modifying
    void deleteEmployeeByUuid(String uuid);

    Optional<Employee> findEmployeeByUuid(String uuid);
}
