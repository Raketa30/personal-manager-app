package ru.liga.application.security.jwt;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class JwtUser implements Serializable {
    private String username;
    private String password;
}
