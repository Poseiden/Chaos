package com.poseiden.configuration.security;

import com.poseiden.domain.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
public class UserAuthorization implements UserDetails {
    private String username;
    private String password;
    private Role role;
    private List<? extends GrantedAuthority> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public UserAuthorization(String username,
                             String password,
                             Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = AuthorityUtils.createAuthorityList(role.value());
    }
}
