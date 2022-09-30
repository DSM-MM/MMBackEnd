package com.example.mmproject.domain.project.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
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
import java.util.UUID;

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
}
}
