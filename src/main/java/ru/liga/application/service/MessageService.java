package ru.liga.application.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@RequiredArgsConstructor
public class MessageService { //todo добавить интерфейс и использовать через него
    public static final Locale LOCALE_RU = new Locale("ru", "RU");

    private final ResourceBundleMessageSource messageSource;

    public String getMessage(String className) {
        try {
            return messageSource.getMessage(className, null, LOCALE_RU);
        } catch (Exception e) {
            return className;
        }
    }

    public String getMessage(@NonNull Enum<?> a) {
        String className = a.getClass().getName();
        return getMessage(className + "." + a);
    }
}
