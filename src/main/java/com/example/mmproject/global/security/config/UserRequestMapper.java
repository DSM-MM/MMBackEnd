package com.example.mmproject.global.security.config;

import com.example.mmproject.domain.user.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {
    public User toDto(OAuth2User oAuth2User) {
        var attributes = oAuth2User.getAttributes();
        return User.builder()
                .email((String)attributes.get("email"))
                .name((String)attributes.get("name"))
                .build();
    }
}
