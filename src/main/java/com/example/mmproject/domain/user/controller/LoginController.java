package com.example.mmproject.domain.user.controller;

import com.example.mmproject.domain.user.controller.dto.UserRequest;
import com.example.mmproject.domain.user.entity.User;
import com.example.mmproject.domain.user.repository.UserRepository;
import com.example.mmproject.global.security.auth.enums.Role;
import com.example.mmproject.global.security.jwt.JwtTokenProvider;
import com.example.mmproject.global.security.jwt.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final JwtTokenProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public void signup(@RequestBody UserRequest dto){
        if(userRepository.existsByEmail(dto.getEmail())){
            log.error("이미 가입한 Email 입니다.");
        }else userRepository.save(User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .introduction(dto.getIntroduction())
                .jobGroup(dto.getJobGroup())
                .role(Role.ROLE_USER).build());
    }


    @PostMapping("/login")
    public TokenDto login(@RequestBody UserRequest dto){
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("가입하지 않은 Email (ㅡ_ㅡ)"));
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new IllegalStateException("잘못된 비밀번호 입니다.");
        }
        return jwtProvider.createToken(user.getEmail(), user.getRole());
    }
}
