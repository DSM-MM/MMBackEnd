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

    //프로젝트 공고글 작성
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

    //프로젝트 공고글 수정
    @Transactional
    public Project update(ProjectRequest request, Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id was wrong"));
        project.update(request);
        return project;
    }

    //프로젝트 공고글 삭제
    @Transactional
    public void delete(Long id){
        projectRepository.deleteById(id);
    }

    //프로젝트 공고글 자세히보기
    @Transactional
    public Project detail(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
    }
}
