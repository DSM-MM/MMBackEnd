package com.example.mmproject.domain.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String name; // 닉넴

    private String email; // 이메일

    private String password; // 비밀번호

    private String newPassword; // 새로운 비밀번호

    private String newPasswordValid; // 새로운 비밀번호 검사

    private String introduction; // 한줄소개

    private String jobGroup; // 직군

    private String language; // 언어

    private String githubLink; // 깃허브 링크
}
