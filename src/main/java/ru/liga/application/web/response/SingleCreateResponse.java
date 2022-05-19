package ru.liga.application.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingleCreateResponse<T> {
    private T dto;
    private List<String> messages;
}
