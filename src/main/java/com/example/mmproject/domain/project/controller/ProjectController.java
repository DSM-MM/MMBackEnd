package com.example.mmproject.domain.project.controller;

import com.example.mmproject.domain.project.controller.dto.ProjectDto;
import com.example.mmproject.domain.project.controller.dto.ProjectRequest;
import com.example.mmproject.domain.project.entity.Project;
import com.example.mmproject.domain.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/project")
    public ProjectDto create(@RequestBody ProjectRequest request){
        return projectService.create(request);
    }

    @PatchMapping("/project/{id}")
    public ProjectDto update(@PathVariable Long id, @RequestBody ProjectRequest request){
        return projectService.update(request, id);
    }

    @DeleteMapping("/project/{id}")
    public void delete(@PathVariable Long id){
        projectService.delete(id);
    }

    @GetMapping("/project/{id}")
    public ProjectDto detail(@PathVariable Long id){
        return projectService.detail(id);
    }

    @PostMapping("/project/upload")
    public String upload(MultipartFile multipartFile) throws Exception{
        return projectService.upload(multipartFile);
    }

    @GetMapping("/project/list")
    public List<ProjectDto> projectList(){
        return projectService.projectList();
    }
}
