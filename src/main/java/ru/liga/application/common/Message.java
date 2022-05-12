package ru.liga.application.common;

public enum Message { //todo вынеси в пакет type(а его domain) чтоб было из названия пакета понято что это)
    POSITION_NOT_FOUND,
    SALARY_NOT_IN_POSITION_RANGE,
    EMPLOYEE_CREATED_SUCCESSFULLY,
    EMPLOYEE_UPDATED_SUCCESSFULLY,
    EMPTY_FIRSTNAME,
    EMPTY_LASTNAME,
    EMPTY_POSITION,
    EMPTY_DEPARTMENT,
    WRONG_ID_DURING_REGISTRATION,
    WRONG_ID_DURING_UPDATE,
    EMPLOYEE_NOT_FOUND,
}
