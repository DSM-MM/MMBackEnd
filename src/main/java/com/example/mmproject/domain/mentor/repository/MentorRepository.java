package com.example.mmproject.domain.mentor.repository;

import com.example.mmproject.domain.mentor.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findAllByOrderByRatingDesc();
}
