package ru.liga.application.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class MultiCreateResponse<T> {
    private Map<T, List<String>> dtoErrorMap;
}
