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
public class MultiCreateResponse<T> {
    @Setter
    private List<T> created;
    private final Map<T, List<String>> notValidDtoWithError = new HashMap<>();

    public void putNotValidDtoWithErrorList(T dto, List<String> errors) {
        notValidDtoWithError.put(dto, errors);
    }
}
