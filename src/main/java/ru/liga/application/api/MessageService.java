package ru.liga.application.api;

import lombok.NonNull;

public interface MessageService {
    String getMessage(String className);

    String getMessage(@NonNull Enum<?> a);
}
