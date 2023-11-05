package com.luca.giornale.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luca.giornale.dto.ChatDto;
import com.luca.giornale.dto.ChatMessageDTO;
import com.luca.giornale.dto.MessageDTO;
import com.luca.giornale.entities.Chat;
import com.luca.giornale.entities.ChatMessages;
import com.luca.giornale.entities.User;
import com.luca.giornale.enums.UserRole;
import com.luca.giornale.exception.ChatNotFoundException;
import com.luca.giornale.exception.UserNotFoundException;
import com.luca.giornale.repository.ChatMessagesRepository;
import com.luca.giornale.repository.ChatRepository;
import com.luca.giornale.repository.UserRepository;
import com.luca.giornale.service.ChatMessagesService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ChatMessagesServiceImpl implements ChatMessagesService{
	@Autowired
    private ChatMessagesRepository chatMessagesRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private ChatRepository chatRepository;
	public ChatMessages createChat(MessageDTO messageDTO) {
	    Long chatId = messageDTO.getChatId();
	    String content = messageDTO.getContent();
	    Chat chat = chatRepository.findById(chatId).orElse(null);
	    User sender = chat.getSender();
        User receiver = chat.getReceiver();
	    // Create and set the message details
		ChatMessages message = new ChatMessages();
		message.setChat(chat);
		message.setContent(content);
		message.setSender(sender);
		message.setReceiver(receiver);
		message.setTimestamp(LocalDateTime.now());

		// Determine the user's role based on the chat
		
		if (sender.getEmail().equals(messageDTO.getSender())) {
		    message.setUserRole(UserRole.SENDER);
		} else if (receiver.getEmail().equals(messageDTO.getSender())) {
		    message.setUserRole(UserRole.RECEIVER);
		}

		// Add the message to the chat's list of messages
		if (chat.getMessages() == null) {
		    chat.setMessages(new ArrayList<>());
		}
		chat.getMessages().add(message);

		// Save the chat to update the messages list
		chatRepository.save(chat);
		chatMessagesRepository.save(message);
		return message;
	}
	
	
	
    

}
