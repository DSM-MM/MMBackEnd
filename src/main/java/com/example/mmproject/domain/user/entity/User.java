package com.example.mmproject.domain.user.entity;

import com.example.mmproject.domain.project.entity.Project;
import com.example.mmproject.global.security.auth.enums.Role;
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
@Table(name = "USERS")
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
    private String language; // 언어x

    @Column
    private String githubLink; // 깃허브 링크

    @Column
    private String provider; // oauth 로 로그인한 사람 ex) google

    @Column
    private String providerId; // 구글 에서 사용하는 아이디 ex) sub = 116233748764850992230

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role; // 권한


}
