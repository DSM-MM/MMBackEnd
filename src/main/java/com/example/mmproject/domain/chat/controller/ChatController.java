package com.example.mmproject.domain.chat.controller;

import com.example.mmproject.domain.chat.controller.dto.RoomInfoRequest;
import com.example.mmproject.domain.chat.entity.Room;
import com.example.mmproject.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat/room")
    public Room createAndFind(@RequestBody RoomInfoRequest request){
        return chatService.createAndFind(request);
    }

    @GetMapping("/chat/room/{roodId}")
    public void roomDetail(@PathVariable Long roodId, Model model){
        model.addAttribute("roomId", roodId);
    }
}
