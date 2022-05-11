package ru.liga.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.application.domain.entity.Department;
import ru.liga.application.repository.DepartmentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Optional<Department> findDepartmentByTitle(String departmentTitle) { //todo не используется
        return departmentRepository.findDepartmentByTitle(departmentTitle);
    }
}
