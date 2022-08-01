package com.example.mmproject.domain.chat.controller;

import com.example.mmproject.domain.chat.controller.dto.MessageRequest;
import com.example.mmproject.domain.chat.entity.Message;
import com.example.mmproject.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void send(@RequestBody MessageRequest request){
        Message message = chatService.saveMessage(request);
        sendingOperations.convertAndSend("/topic/chat/room"+message.getRoom().getId(), message);
    }
}
