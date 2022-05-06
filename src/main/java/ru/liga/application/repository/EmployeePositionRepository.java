package ru.liga.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.application.domain.entity.EmployeePosition;

import java.util.Optional;

@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {
    @Query("select e from EmployeePosition e where e.title = ?1 and e.department.title = ?2")
    Optional<EmployeePosition> findByTitleAndDepartmentTitle(String positionTitle, String departmentTitle);
}
