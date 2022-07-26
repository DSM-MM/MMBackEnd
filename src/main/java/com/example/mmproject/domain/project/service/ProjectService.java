package com.example.mmproject.domain.project.service;

import com.example.mmproject.domain.project.controller.dto.ProjectRequest;
import com.example.mmproject.domain.project.entity.Project;
import com.example.mmproject.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public Project create(ProjectRequest request){
        return projectRepository.save(Project.builder()
                        .title(request.getTitle())
                        .period(request.getPeriod())
                        .content(request.getContent())
                        .needed(request.getNeeded())
                        .preference(request.getPreference())
                .build());
    }

    @Transactional
    public Project update(ProjectRequest request, Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id was wrong"));
        project.update(request);
        return project;
    }

    @Transactional
    public void delete(Long id){
        projectRepository.deleteById(id);
    }

    @Transactional
    public Project detail(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
    }
}
