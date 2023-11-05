package com.luca.giornale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.giornale.dto.MessageDTO;
import com.luca.giornale.entities.ChatMessages;
import com.luca.giornale.exception.ChatNotFoundException;
import com.luca.giornale.service.ChatMessagesService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private ChatMessagesService chatMessagesService;

    @PostMapping(value= "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMessage(@RequestBody MessageDTO messageDTO) {
        try {
            ChatMessages message = chatMessagesService.createChat(messageDTO);
            return ResponseEntity.ok(message); // Return the created message or a success response.
        } catch (ChatNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // Handle the exception.
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred."); // Handle other exceptions.
        }
    }
  
}
