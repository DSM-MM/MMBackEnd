package com.example.mmproject.domain.chat.service;

import com.example.mmproject.domain.chat.controller.dto.MessageRequest;
import com.example.mmproject.domain.chat.controller.dto.RoomInfoRequest;
import com.example.mmproject.domain.chat.entity.Message;
import com.example.mmproject.domain.chat.entity.Room;
import com.example.mmproject.domain.chat.repository.MessageRepository;
import com.example.mmproject.domain.chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;

    //채팅방 생성 및 찾기
    @Transactional
    public Room createAndFind(RoomInfoRequest request){
        Room room = roomRepository.findByMentorAndMentee(request.getMentor(), request.getMentee());
        if(room != null) return room;
        else{
            Room newRoom = new Room(request.getMentor(), request.getMentee());
            return roomRepository.save(newRoom);
        }
    }

    //메세지 db 저장
    @Transactional
    public Message saveMessage(MessageRequest request){
        Room room = roomRepository.findByMentorAndMentee(request.getMentor(), request.getMentee());
        if(room == null){
            throw new IllegalArgumentException("not found");
        }

        Message message = new Message(request.getSender(), request.getMessage(), room);
        return messageRepository.save(message);
    }
}
