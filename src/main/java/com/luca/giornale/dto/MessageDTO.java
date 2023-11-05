package com.luca.giornale.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class MessageDTO {
	  private Long chatId;
	    private String sender;
	    
	    private String content;
	   

}
