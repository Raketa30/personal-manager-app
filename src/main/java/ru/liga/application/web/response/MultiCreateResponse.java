package ru.liga.application.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class MultiCreateResponse<T> { //todo пенести в domain а там в пакет response
    private final Map<T, List<String>> notValidDtoWithError = new HashMap<>();

    public void putNotValidDtoWithErrorList(T dto, List<String> errors) { //todo тогда этот метод нужно перенести в другое место)
        notValidDtoWithError.put(dto, errors);
    }
}
