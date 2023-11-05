package com.luca.giornale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.giornale.dto.ChatDto;
import com.luca.giornale.entities.Chat;
import com.luca.giornale.entities.ChatMessages;
import com.luca.giornale.service.ChatService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders ="*")
public class ChatController {
    
	@Autowired
	private ChatService chatService;

    @PostMapping(value= "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chat> createChat(@RequestBody ChatDto chatDTO) {
        Chat chat = chatService.createChat(chatDTO);
        if (chat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(chat);
    }
    
    @GetMapping("/user/{userEmail}")
    public ResponseEntity<List<Chat>> getChatsForUser(@PathVariable String userEmail) {
        List<Chat> userChats = chatService.getChatsForUser(userEmail);
        return ResponseEntity.ok(userChats);
    }
    
    @GetMapping("/messages/{chatId}")
    public ResponseEntity<List<ChatMessages>> getMessagesByChatId(@PathVariable Long chatId) {
        List<ChatMessages> messages = chatService.getMessagesByChatId(chatId);

        if (messages == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(messages);
    }
}
