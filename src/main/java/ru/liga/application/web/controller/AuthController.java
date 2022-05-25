package ru.liga.application.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.application.domain.dto.AuthDto;
import ru.liga.application.security.jwt.JwtTokenProvider;
import ru.liga.application.security.user.JwtUser;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static ru.liga.application.web.controller.AuthController.AUTH_URL;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(AUTH_URL)
public class AuthController {
    public static final String AUTH_URL = "/api/v1/auth";
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid AuthDto authDto) {
        log.debug("AuthController auth() authDto: {}", authDto);
        try {
            String username = authDto.getUsername();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, authDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtTokenProvider.createToken(new JwtUser(userDetails.getUsername(), userDetails.getPassword()));
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("jwt", jwtToken);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.info("AuthController auth: AuthenticationException", e);
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
