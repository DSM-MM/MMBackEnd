package com.example.mmproject.domain.chat.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String roomId;
    private String sender;
    private String message;
}
