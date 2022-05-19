package ru.liga.application.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
//todo перенести в domain а там в пакет response
// done
public class SingleCreateResponse<T> {
    private T dto;
    private List<String> messages;
}
