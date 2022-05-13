package ru.liga.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.liga.application.domain.entity.Position;

import java.util.Optional;

@Repository
public interface EmployeePositionRepository extends JpaRepository<Position, Long> {
    @Query("select e from Position e where e.title = ?1 and e.department.title = ?2")
    Optional<Position> findByTitleAndDepartmentTitle(String positionTitle, String departmentTitle);
}
