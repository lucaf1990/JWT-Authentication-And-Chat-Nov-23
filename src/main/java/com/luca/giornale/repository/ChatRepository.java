package com.luca.giornale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luca.giornale.entities.Chat;
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
	   @Query("SELECT c FROM Chat c LEFT JOIN FETCH c.sender WHERE c.sender.email = :email OR c.receiver.email = :email")
	List<Chat> findBySenderEmailOrReceiverEmail(String email);
	

}
