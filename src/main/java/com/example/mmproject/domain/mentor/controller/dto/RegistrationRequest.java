package com.example.mmproject.domain.mentor.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String name;
    private String major;
    private String email;
    private String introduction;
    private String language;
    private String jobGroup;
}
