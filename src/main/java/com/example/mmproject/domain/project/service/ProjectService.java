package com.example.mmproject.domain.project.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.mmproject.domain.project.controller.dto.ProjectDto;
import com.example.mmproject.domain.project.controller.dto.ProjectRequest;
import com.example.mmproject.domain.project.entity.Image;
import com.example.mmproject.domain.project.entity.Project;
import com.example.mmproject.domain.project.repository.ImageRepository;
import com.example.mmproject.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final ProjectRepository projectRepository;
    private final ImageRepository imageRepository;

    //프로젝트 공고글 작성
    @Transactional
    public ProjectDto create(ProjectRequest request){
        Project project = projectRepository.save(Project.builder()
                        .title(request.getTitle())
                        .period(request.getPeriod())
                        .content(request.getContent())
                        .needed(request.getNeeded())
                        .preference(request.getPreference())
                .build());
        return ProjectDto.builder()
                .id(project.getId())
                .images(project.getImages())
                .content(project.getContent())
                .needed(project.getNeeded())
                .preference(project.getPreference())
                .period(project.getPeriod())
                .user(project.getUser())
                .title(project.getTitle())
                .build();
    }

    //프로젝트 공고글 수정
    @Transactional
    public ProjectDto update(ProjectRequest request, Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id was wrong"));
        project.update(request);
        return ProjectDto.builder()
                .id(project.getId())
                .images(project.getImages())
                .content(project.getContent())
                .needed(project.getNeeded())
                .preference(project.getPreference())
                .period(project.getPeriod())
                .user(project.getUser())
                .title(project.getTitle())
                .build();
    }

    //프로젝트 공고글 삭제
    @Transactional
    public void delete(Long id){
        projectRepository.deleteById(id);
    }

    //프로젝트 공고글 자세히보기
    @Transactional
    public ProjectDto detail(Long id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
        return ProjectDto.builder()
                .id(project.getId())
                .images(project.getImages())
                .content(project.getContent())
                .needed(project.getNeeded())
                .preference(project.getPreference())
                .period(project.getPeriod())
                .user(project.getUser())
                .title(project.getTitle())
                .build();
    }

    @Transactional
    public String upload(MultipartFile multipartFile) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        Image saveImage = new Image(s3FileName);

        imageRepository.save(saveImage);

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    @Transactional
    public List<ProjectDto> projectList(){
        List<Project> projectList = projectRepository.findAll();
        return projectList.stream().map(p -> new ProjectDto(
                p.getId(),
                p.getUser(),
                p.getTitle(),
                p.getPeriod(),
                p.getContent(),
                p.getNeeded(),
                p.getPreference(),
                p.getImages())).collect(Collectors.toList());
    }
}

