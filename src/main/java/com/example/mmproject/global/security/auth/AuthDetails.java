package com.example.mmproject.global.security.auth;

import com.example.mmproject.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class AuthDetails implements UserDetails {

    private final User user;

    //사용자가 가진 모든 권한 정보를 얻는다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
        return roles;
    }

    @Override
    public String getPassword() { // 계정
        return user.getPassword();
    }

    @Override
    public String getUsername() { // 계정
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { // 계정 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 잠금 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // pw의 만료 여부
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정 사용 가능 여부
        return true;
    }
}