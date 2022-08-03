package com.example.mmproject.domain.project.repository;

import com.example.mmproject.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
