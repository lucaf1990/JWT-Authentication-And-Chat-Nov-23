package com.luca.giornale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luca.giornale.entities.Chat;
import com.luca.giornale.entities.ChatMessages;
@Repository
public interface ChatMessagesRepository extends JpaRepository<ChatMessages, Long>{
	List<ChatMessages> findBySenderEmailOrReceiverEmail(String senderEmail, String receiverEmail);

	List<ChatMessages> findByChatId(Long chatId);
}
