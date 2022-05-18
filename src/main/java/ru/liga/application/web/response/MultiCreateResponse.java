package ru.liga.application.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
//todo должен лежать в другом пакете
public class MultiCreateResponse<T> implements AbstractResponse {
    @Setter
    private List<T> created;
    private final Map<T, List<String>> notValidDtoWithError = new HashMap<>();

    @Override
    public boolean hasErrors() {
        return !notValidDtoWithError.isEmpty();
    }

    public void putNotValidDtoWithErrorList(T dto, List<String> errors) {
        notValidDtoWithError.put(dto, errors);
    }
}
