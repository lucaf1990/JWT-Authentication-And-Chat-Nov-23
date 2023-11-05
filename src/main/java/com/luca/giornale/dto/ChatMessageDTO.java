package com.luca.giornale.dto;

import java.time.LocalDateTime;

import com.luca.giornale.entities.User;

import lombok.Data;
@Data
public class ChatMessageDTO {
    private Long messageId;
    private String content;
    private LocalDateTime timestamp;
    private User sender;
    private User receiver;

    // Getters and setters here...
}