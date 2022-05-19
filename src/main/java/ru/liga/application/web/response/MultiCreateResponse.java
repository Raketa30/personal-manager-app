package ru.liga.application.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
//todo должен лежать в другом пакете
// done
public class MultiCreateResponse<T> {
    private final Map<T, List<String>> notValidDtoWithError = new HashMap<>();

    public void putNotValidDtoWithErrorList(T dto, List<String> errors) {
        notValidDtoWithError.put(dto, errors);
    }
}
