package com.example.mmproject.domain.project.controller.dto;

import com.example.mmproject.domain.project.entity.Image;
import com.example.mmproject.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    private User user;
    private String title;
    private String period;
    private String content;
    private String needed;
    private String preference;
    private List<Image> images;
}
