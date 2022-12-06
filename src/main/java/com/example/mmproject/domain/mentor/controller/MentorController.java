package com.example.mmproject.domain.mentor.controller;

import com.example.mmproject.domain.mentor.controller.dto.RatingRequest;
import com.example.mmproject.domain.mentor.controller.dto.RegistrationRequest;
import com.example.mmproject.domain.mentor.entity.Mentor;
import com.example.mmproject.domain.mentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;

    @PostMapping("/mentor")
    public Mentor registration(@RequestBody RegistrationRequest request){
        return mentorService.registration(request);
    }

    @DeleteMapping("/mentor/{id}")
    public void delete(@PathVariable Long id){
        mentorService.delete(id);
    }

    @GetMapping("/mentor/{id}")
    public Mentor detail(@PathVariable Long id){
        return mentorService.detail(id);
    }

    @PostMapping("/rating/{id}")
    public void rating(@PathVariable Long id, @RequestBody RatingRequest request){
        mentorService.rating(request, id);
    }

    @GetMapping("/mentor/list")
    public List<Mentor> mentorList(){
        return mentorService.mentorList();
    }

    @GetMapping("/mentor/top3")
    public List<Mentor> mentorTop3(){
        return mentorService.mentorTop3();
    }
}
