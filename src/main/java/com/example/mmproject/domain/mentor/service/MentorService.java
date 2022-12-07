package com.example.mmproject.domain.mentor.service;

import com.example.mmproject.domain.mentor.controller.dto.MentorDto;
import com.example.mmproject.domain.mentor.controller.dto.RatingRequest;
import com.example.mmproject.domain.mentor.controller.dto.RegistrationRequest;
import com.example.mmproject.domain.mentor.entity.Mentor;
import com.example.mmproject.domain.mentor.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;

    //멘토 등록
    @Transactional
    public MentorDto registration(RegistrationRequest request){
        Mentor mentor = mentorRepository.save(Mentor.builder()
                        .name(request.getName())
                        .major(request.getMajor())
                        .email(request.getEmail())
                        .introduction(request.getIntroduction())
                        .language(request.getLanguage())
                        .jobGroup(request.getJobGroup())
                .build());
        return MentorDto.builder()
                .id(mentor.getId())
                .name(mentor.getName())
                .introduction(mentor.getIntroduction())
                .major(mentor.getMajor())
                .email(mentor.getEmail())
                .language(mentor.getLanguage())
                .rating(mentor.getRating())
                .jobGroup(mentor.getJobGroup())
                .build();
    }

    //멘토 등록 삭제
    @Transactional
    public void delete(Long id){
        mentorRepository.deleteById(id);
    }

    //멘토 자세히보기
    @Transactional
    public MentorDto detail(Long id){
        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
        return MentorDto.builder()
                .id(mentor.getId())
                .name(mentor.getName())
                .introduction(mentor.getIntroduction())
                .major(mentor.getMajor())
                .email(mentor.getEmail())
                .language(mentor.getLanguage())
                .rating(mentor.getRating())
                .jobGroup(mentor.getJobGroup())
                .build();
    }

    //평점 시스템
    @Transactional
    public void rating(RatingRequest request, Long id){
        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id was wrong"));
        double ratingAvg = (mentor.getRating() * (mentor.getTotalCount()) + request.getRating()) / (mentor.getTotalCount() + 1);
        mentor.rating(mentor.getTotalCount() + 1, ratingAvg);
        mentorRepository.save(mentor);
    }

    //멘토 리스트
    @Transactional
    public List<MentorDto> mentorList(){
        List<Mentor> mentorList = mentorRepository.findAll();
        return mentorList.stream().map(p -> new MentorDto(
                p.getId(),
                p.getName(),
                p.getMajor(),
                p.getEmail(),
                p.getIntroduction(),
                p.getLanguage(),
                p.getRating(),
                p.getJobGroup())).collect(Collectors.toList());
    }

    @Transactional
    public List<MentorDto> mentorTop3(){
        List<Mentor> mentorList = mentorRepository.findAllByOrderByRatingDesc();
        return mentorList.stream().map(p -> new MentorDto(
                p.getId(),
                p.getName(),
                p.getMajor(),
                p.getEmail(),
                p.getIntroduction(),
                p.getLanguage(),
                p.getRating(),
                p.getJobGroup())).collect(Collectors.toList());
    }
}
