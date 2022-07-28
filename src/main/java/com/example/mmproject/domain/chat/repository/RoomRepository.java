package com.example.mmproject.domain.chat.repository;

import com.example.mmproject.domain.chat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByMentorAndMentee(String mentor, String mentee);
}
