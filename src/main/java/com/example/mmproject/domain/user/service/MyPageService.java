package com.example.mmproject.domain.user.service;

import com.example.mmproject.domain.user.controller.dto.UserRequest;
import com.example.mmproject.domain.user.entity.User;
import com.example.mmproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 마이페이지 보기
    public User getMyPage(Long id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("USER_ID_NOT_FOUND"));
    }

    // 마이페이지 수정
    @Transactional
    public void setMyPage(Long id, UserRequest userRequest){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("USER_ID_NOT_FOUND"));
        user.setMyPage(userRequest.getIntroduction(), userRequest.getJobGroup(), userRequest.getLanguage(), userRequest.getGithubLink());
        userRepository.save(user);
    }

    // 이전비밀번호 검사, 새로운 비밀번호 검사
    @Transactional
    public void setPassword(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("USER_ID_NOT_FOUND"));
        try {
            // 이전 비밀번호 맞는지 검사
            if (passwordEncoder.matches(user.getPassword(),userRequest.getPassword())) {
                try {
                    // 변경할 비밀번호를 입력을 잘했는지 검사
                    if (userRequest.getNewPassword().equals(userRequest.getNewPasswordValid())) {
                        user.setPassword(passwordEncoder.encode(userRequest.getNewPassword()));
                        userRepository.save(user);
                        log.info("변경 되었습니다.");
                    } else throw new Exception();
                } catch (Exception e) {
                    log.error("USER_NEW_PASSWORD_NOT_EQUALS");
                }
            } else throw new Exception();
        } catch (Exception e) {
            log.error("USER_ORIGINAL_PASSWORD_NOT_EQUALS");
        }
    }
}
