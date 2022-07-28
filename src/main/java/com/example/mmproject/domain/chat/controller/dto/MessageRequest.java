package com.example.mmproject.domain.chat.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private String message;
    private String sender;
    private String mentor;
    private String mentee;
}
