package ru.liga.application.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.liga.application.web.RestUrlV1.DEPARTMENTS_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(DEPARTMENTS_URL)
public class DepartmentController {
}
