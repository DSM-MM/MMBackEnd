package com.example.mmproject.domain.user.controller;

import com.example.mmproject.domain.user.controller.dto.UserRequest;
import com.example.mmproject.domain.user.entity.User;
import com.example.mmproject.domain.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    // 마이페이지 보기
    @GetMapping("/{id}")
    public User getMyPage(@PathVariable Long id){
        return myPageService.getMyPage(id);
    }

    // 마이페이지 수정
    @PatchMapping("/{id}")
    public void setMyPage(@PathVariable Long id, @RequestBody UserRequest request){
        myPageService.setMyPage(id, request);
    }

    // 마이페이지 비밀번호 수정
    @PatchMapping("/password/{id}")
    public void setMyPassword(@PathVariable Long id, @RequestBody UserRequest request){
        myPageService.setPassword(id, request);
    }
}
