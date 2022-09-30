package com.example.mmproject.domain.project.repository;

import com.example.mmproject.domain.project.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
