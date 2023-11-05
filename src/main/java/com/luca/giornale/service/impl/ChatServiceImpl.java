package com.luca.giornale.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luca.giornale.dto.ChatDto;
import com.luca.giornale.entities.Chat;
import com.luca.giornale.entities.ChatMessages;
import com.luca.giornale.entities.User;
import com.luca.giornale.exception.UserNotFoundException;
import com.luca.giornale.repository.ChatRepository;
import com.luca.giornale.repository.UserRepository;
import com.luca.giornale.service.ChatService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
	private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public Chat createChat(ChatDto chatDTO) {
        User sender = userRepository.findByUserEmail(chatDTO.getSenderEmail());
        User receiver = userRepository.findByUserEmail(chatDTO.getReceiverEmail());

        if (sender == null || receiver == null) {
           throw new UserNotFoundException("Sender or receiver not found");
        }

        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setReceiver(receiver);
        chat.setChatName(chatDTO.getChatName());

        // You can set additional properties from the DTO if needed

        return chatRepository.save(chat);
    }
    
    
    public List<Chat> getChatsForUser(String userEmail) {
        // Use the repository method to retrieve chats for the user
        return chatRepository.findBySenderEmailOrReceiverEmail(userEmail, userEmail);
    }
    
    public List<ChatMessages> getMessagesByChatId(Long chatId) {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        
        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();
            List<ChatMessages> messages = chat.getMessages();
            return messages;
        } else {
            // Handle the case where the chat with the provided ID does not exist
            return null; // or throw an exception, return an error message, etc.
        }
    }
}
