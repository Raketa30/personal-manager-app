package ru.liga.application.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.liga.application.api.MessageService;

import java.util.Locale;
//todo добавить интерфейс и использовать через него
// done
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    public static final Locale LOCALE_RU = new Locale("ru", "RU");

    private final ResourceBundleMessageSource messageSource;

    @Override
    public String getMessage(String className) {
        try {
            return messageSource.getMessage(className, null, LOCALE_RU);
        } catch (Exception e) {
            return className;
        }
    }

    @Override
    public String getMessage(@NonNull Enum<?> a) {
        String className = a.getClass().getName();
        return getMessage(className + "." + a);
    }
}
