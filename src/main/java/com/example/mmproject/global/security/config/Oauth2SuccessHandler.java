package com.example.mmproject.global.security.config;

import com.example.mmproject.domain.user.controller.dto.UserDto;
import com.example.mmproject.domain.user.controller.dto.UserRequest;
import com.example.mmproject.domain.user.entity.User;
import com.example.mmproject.global.security.auth.enums.Role;
import com.example.mmproject.global.security.jwt.JwtTokenProvider;
import com.example.mmproject.global.security.jwt.dto.TokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Token;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Component
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider tokenService;
    private final UserRequestMapper userRequestMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);

        log.info("Principal에서 꺼낸 oauth2User : {}", oAuth2User);
        // 최초 로그인이라면 회원가입 처리를 한다.

        log.info("토큰 발행 시작");

        TokenDto token = tokenService.createToken(userDto.getEmail(), Role.ROLE_USER);
        log.info("AccessToken : {}", token.getAccessToken());
        log.info("RefreshToken : {}", token.getRefreshToken());
        log.info("Token : {}", token);

        String url = makeRedirectUrl(token.getAccessToken(), token.getRefreshToken());
        log.info("url : {}", url);

        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String accessToken, String refreshToken){
        return UriComponentsBuilder.fromUriString("http://ec2-43-200-178-101.ap-northeast-2.compute.amazonaws.com/oauth2/redirect/" + "accessToken=" + accessToken + "&" + "refreshToken=" + refreshToken)
                .build().toUriString();
    }
}
