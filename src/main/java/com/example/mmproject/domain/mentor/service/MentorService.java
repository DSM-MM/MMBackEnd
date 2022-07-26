package com.example.mmproject.domain.mentor.service;

import com.example.mmproject.domain.mentor.controller.dto.RatingRequest;
import com.example.mmproject.domain.mentor.controller.dto.RegistrationRequest;
import com.example.mmproject.domain.mentor.entity.Mentor;
import com.example.mmproject.domain.mentor.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;

    @Transactional
    public Mentor registration(RegistrationRequest request){
        return mentorRepository.save(Mentor.builder()
                        .name(request.getName())
                        .major(request.getMajor())
                        .email(request.getEmail())
                        .introduction(request.getIntroduction())
                        .language(request.getLanguage())
                        .jobGroup(request.getJobGroup())
                .build());
    }

    @Transactional
    public void delete(Long id){
        mentorRepository.deleteById(id);
    }

    @Transactional
    public Mentor detail(Long id){
        return mentorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
    }

    @Transactional
    public void rating(RatingRequest request, Long id){
        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
        double ratingAvg = (mentor.getRating() * (mentor.getTotalCount()) + request.getRating()) / (mentor.getTotalCount() + 1);
        mentor.rating(mentor.getTotalCount() + 1, ratingAvg);
        mentorRepository.save(mentor);
    }
}
