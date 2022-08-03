package com.example.mmproject.global.security.oauth;

import com.example.mmproject.domain.user.entity.User;
import com.example.mmproject.domain.user.repository.UserRepository;
import com.example.mmproject.global.security.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    // 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
        String providerId = oauth2User.getAttribute("sub");
        String email = oauth2User.getAttribute("email");
        String password = "1234";
        Role role = Role.ROLE_USER;

        if (!userRepository.existsByEmail(email)){
            userRepository.save(User.builder()
                    .email(email)
                    .name("google_" + providerId)
                    .password(password)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId).build());
        }else log.error("이미 계정이 있습니다.");
        return super.loadUser(userRequest);
    }
}
