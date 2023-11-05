package com.luca.giornale.service;

import java.util.List;

import com.luca.giornale.dto.ChatDto;
import com.luca.giornale.entities.Chat;
import com.luca.giornale.entities.ChatMessages;

public interface ChatService {
	public Chat createChat(ChatDto chatDTO);
	public List<Chat> getChatsForUser(String userEmail);
	public List<ChatMessages> getMessagesByChatId(Long chatId);
}
