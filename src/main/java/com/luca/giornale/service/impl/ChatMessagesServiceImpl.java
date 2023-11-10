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

	    if (chat == null) {
	        // Handle the case when the chat is not found
	        return null;
	    }

	    User currentUser = userRepository.findByEmail(messageDTO.getSender());

	    if (currentUser == null) {
	        // Handle the case when the user is not found
	        return null;
	    }

	    User sender = chat.getSender();
	    User receiver = chat.getReceiver();

	    // Check if the current user is part of the chat
	    if (!currentUser.getEmail().equals(sender.getEmail()) && !currentUser.getEmail().equals(receiver.getEmail())) {
	        // Handle the case when the current user is not part of the chat
	        return null;
	    }

	    // Create and set the message details
	    ChatMessages message = new ChatMessages();
	    message.setChat(chat);
	    message.setContent(content);

	    // Determine the sender and receiver based on the current user
	    if (currentUser.getEmail().equals(sender.getEmail())) {
	        message.setSender(sender);
	        message.setReceiver(receiver);
	        message.setUserRole(UserRole.SENDER);
	    } else if (currentUser.getEmail().equals(receiver.getEmail())) {
	        message.setSender(receiver);
	        message.setReceiver(sender);
	        message.setUserRole(UserRole.RECEIVER);
	    } else {
	        // Handle the case when the sender's email doesn't match the chat's sender or receiver
	        return null;
	    }

	    message.setTimestamp(LocalDateTime.now());

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
