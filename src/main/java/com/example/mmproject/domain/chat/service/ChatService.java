package com.example.mmproject.domain.chat.service;

import com.example.mmproject.domain.chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final RoomRepository roomRepository;


}
