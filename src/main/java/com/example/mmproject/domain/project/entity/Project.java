package com.example.mmproject.domain.project.entity;

import com.example.mmproject.domain.project.controller.dto.ProjectRequest;
import com.example.mmproject.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column
    private String period;

    @Column(nullable = false)
    private String content;

    @Column
    private String needed;

    @Column
    private String preference;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<Image> images;

    public void update(ProjectRequest request){
        this.title = request.getTitle();
        this.period = request.getPeriod();
        this.content = request.getContent();
        this.needed = request.getNeeded();
        this.preference = request.getPreference();
    }
}

