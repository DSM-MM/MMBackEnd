package com.example.mmproject.domain.project.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private String title;
    private String content;
    private String period;
    private String needed;
    private String preference;
}
