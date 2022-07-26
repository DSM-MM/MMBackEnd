package com.example.mmproject.domain.mentor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "MENTOR")
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String major;

    @Column
    private String email;

    @Column
    private String introduction;

    @Column
    private String language;

    @Column
    private String rating;

    @Column
    private String jobGroup;

}
