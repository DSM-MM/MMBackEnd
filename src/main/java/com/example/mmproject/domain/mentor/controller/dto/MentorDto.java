package com.example.mmproject.domain.mentor.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorDto {
    private Long id;
    private String name;
    private String major;
    private String email;
    private String introduction;
    private String language;
    private double rating;
    private String jobGroup;
}
