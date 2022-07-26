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
                        .content(request.getContent())
                        .needed(request.getNeeded())
                        .period(request.getPeriod())
                        .preference(request.getPreference())
                .build());
    }

    @Transactional
    public Project update(ProjectRequest request, Long id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
        project.update(request);
        return projectRepository.save(project);
    }

    @Transactional
    public void delete(Long id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
        projectRepository.delete(project);
    }

    @Transactional
    public Project detail(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
    }
}
