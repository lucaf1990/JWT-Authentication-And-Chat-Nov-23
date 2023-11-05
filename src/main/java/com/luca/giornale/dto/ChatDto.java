package com.luca.giornale.dto;

import com.luca.giornale.entities.User;

import lombok.Data;


@Data
public class ChatDto {


    private String senderEmail;
    private String receiverEmail;
    private String chatName;

}
