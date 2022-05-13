package ru.liga.application.exception;

public class EmployeeValidatorException extends Exception {
    public EmployeeValidatorException(String message) {
        super(message);
    }

    public EmployeeValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
