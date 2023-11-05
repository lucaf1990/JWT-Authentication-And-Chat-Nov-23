package com.luca.giornale.service;

import java.util.List;

import com.luca.giornale.dto.ChatMessageDTO;
import com.luca.giornale.dto.MessageDTO;
import com.luca.giornale.entities.ChatMessages;

public interface ChatMessagesService {
	public ChatMessages createChat(MessageDTO messageDTO);

}
