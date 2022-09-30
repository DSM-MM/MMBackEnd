package com.example.mmproject.domain.user.controller;

import com.example.mmproject.domain.user.controller.dto.UserRequest;
import com.example.mmproject.domain.user.entity.User;
import com.example.mmproject.domain.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    // 마이페이지 보기
    @GetMapping()
    public User getMyPage(Authentication authentication){
        if(authentication == null){
            throw new BadCredentialsException("USER_NOT_FOUND");
        }
        return myPageService.getMyPage(authentication.getName());
    }

    // 마이페이지 수정
    @PatchMapping("/set")
    public void setMyPage(Authentication authentication, @RequestBody UserRequest request){
        myPageService.setMyPage(authentication.getName(), request);
    }

    // 마이페이지 비밀번호 수정
    @PatchMapping("/password")
    public void setMyPassword(Authentication authentication, @RequestBody UserRequest request){
        myPageService.setPassword(authentication.getName(), request);
    }

}
