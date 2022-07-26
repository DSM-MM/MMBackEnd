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
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String introduction;

    @Column
    private String jobGroup;

    @Column
    private String githubLink;
}
