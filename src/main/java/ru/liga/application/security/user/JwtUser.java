package ru.liga.application.security.user;

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
