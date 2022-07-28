package com.example.mmproject.domain.chat.service;

import com.example.mmproject.domain.chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final RoomRepository roomRepository;


}
