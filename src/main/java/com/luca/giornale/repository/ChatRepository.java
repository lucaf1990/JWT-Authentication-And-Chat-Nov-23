package com.luca.giornale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luca.giornale.entities.Chat;
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
	List<Chat> findBySenderEmailOrReceiverEmail(String senderEmail, String receiverEmail);


}
