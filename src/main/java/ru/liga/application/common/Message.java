package ru.liga.application.common;

public class Message {
    //todo оставлять русские слова в коде плохая практика. Используй ResourceBundle. Можно посмотреть в fccr класс MessageService
    public static final String POSITION_NOT_FOUND = "Указанной должности не существует";
    public static final String DEPARTMENT_NOT_FOUND = "Указанного департамента не существует"; //todo не используется
    public static final String USER_EXIST = "";//todo не используется
    public static final String SALARY_NOT_IN_POSITION_RANGE = "У позиции %s, зарплата должна быть в пределах %d до %d, в запросе прислали %d";
    public static final String USER_CREATED_SUCCESSFULLY = "Пользователь успешно создан";
    public static final String USER_UPDATED_SUCCESSFULLY = "Пользователь успешно собновлен";
    public static final String REQUEST_NOT_VALID = "Неверный запрос, проверьте параметры запроса";

    private Message() {
    }
}