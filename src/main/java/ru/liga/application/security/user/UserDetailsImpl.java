package ru.liga.application.security.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.liga.application.security.jwt.JwtUser;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private final JwtUser jwtUser;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(JwtUser jwtUser) {
        this.jwtUser = jwtUser;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return jwtUser.getPassword();
    }

    @Override
    public String getUsername() {
        return jwtUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
