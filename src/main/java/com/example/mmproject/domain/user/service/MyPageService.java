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

    // 자신의 계정 삭제
    public void leaveUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("USER_IS_NOT_FOUND"));
        userRepository.delete(user);
    }

    // 마이페이지 보기
    public User getMyPage(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("USER_ID_NOT_FOUND"));
    }

    // 마이페이지 수정
    public void setMyPage(String email, UserRequest userRequest){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("USER_ID_NOT_FOUND"));
        user.setMyPage(userRequest.getName(),userRequest.getIntroduction(), userRequest.getJobGroup(), userRequest.getLanguage(), userRequest.getGithubLink());
        userRepository.save(user);
    }

    // 이전비밀번호 검사, 새로운 비밀번호 검사
    public void setPassword(String email, UserRequest userRequest) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("USER_ID_NOT_FOUND"));

        // 구글 사용자인지 아닌지
        if(user.getProvider() == null) {
            // 이전 비밀번호 맞는지 검사
            if (passwordEncoder.matches(userRequest.getPassword(), user.getPassword())){
                // 변경할 비밀번호를 입력을 잘했는지 검사
                if (userRequest.getNewPassword().equals(userRequest.getNewPasswordValid())) {
                    user.setPassword(passwordEncoder.encode(userRequest.getNewPassword()));
                    userRepository.save(user);
                    log.info("USER_PASSWORD_CHANGE_SUCCESS");
                } else log.error("USER_NEW_PASSWORD_NOT_EQUALS");

            } else log.error("USER_ORIGINAL_PASSWORD_NOT_EQUALS");
        }else{
            // 이전 비밀번호 맞는지 검사
            if (userRequest.getPassword().equals(user.getPassword())){
                // 변경할 비밀번호를 입력을 잘했는지 검사
                if (userRequest.getNewPassword().equals(userRequest.getNewPasswordValid())) {
                    user.setPassword(passwordEncoder.encode(userRequest.getNewPassword()));
                    userRepository.save(user);
                    log.info("USER_PASSWORD_CHANGE_SUCCESS");
                } else log.error("USER_NEW_PASSWORD_NOT_EQUALS");

            } else log.error("USER_ORIGINAL_PASSWORD_NOT_EQUALS");
        }

    }
}
