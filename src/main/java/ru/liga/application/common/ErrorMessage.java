package ru.liga.application.common;

public class ErrorMessage {
    public static final String WRONG_ID_DURING_REGISTRATION = "При регистрации оставьте поле Id пустым";
    public static final String EMPTY_USERNAME = "Имя не заполнено";
    public static final String EMPTY_LASTNAME = "Фамилия не заполнена";
    public static final String EMPTY_POSITION = "Должность не заполнена";
    public static final String EMPTY_DEPARTMENT = "Департамент не заполнен";
    public static final String WRONG_ID_DURING_UPDATE = "Укажите Id обновляемого пользователя";

    private ErrorMessage() {
    }
}
