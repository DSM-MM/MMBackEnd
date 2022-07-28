package com.example.mmproject.domain.user.entity;

import com.example.mmproject.domain.project.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Project> projects;

    @Column
    private String name; // 닉넴

    @Column
    private String email; // 이메일

    @Column
    private String password; // 비밀번호

    @Column
    private String introduction; // 한줄소개

    @Column
    private String jobGroup; // 직군

    @Column
    private String language; // 언어

    @Column
    private String githubLink; // 깃허브 링크

    // 비밀번호 빼고 수정
    public void setMyPage(String introduction, String jobGroup, String language, String githubLink){
        introduction = this.introduction;
        jobGroup = this.jobGroup;
        language = this.language;
        githubLink = this.githubLink;
    }

    // 비밀번호 수정
    public void setPassword(String password){
        password = this.password;
    }
}
