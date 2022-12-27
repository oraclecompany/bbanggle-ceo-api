package com.oraclecompany.bbanggle.jwt.dto;

import com.oraclecompany.bbanggle.api.login.constant.Role;
import com.oraclecompany.bbanggle.domain.ceo.entity.Ceo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final Ceo ceo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = ceo.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>(); //List인 이유 : 여러개의 권한을 가질 수 있다
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return ceo.getPassword();
    }

    @Override
    public String getUsername() {
        return ceo.getName();
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

    public String getName() {
        return ceo.getName();
    }

    public Long getId() {
        return ceo.getId();
    }

    public Role getRole() {
        return ceo.getRole();
    }
}
