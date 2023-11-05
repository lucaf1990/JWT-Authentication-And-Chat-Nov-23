package com.luca.giornale.entities;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.luca.giornale.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="chatMessages")
@JsonSerialize(using = CustomChatMessageSerializer.class)
public class ChatMessages {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(name = "sender_id", referencedColumnName = "email")
	private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "email")
    private User receiver;

    @ManyToOne
    private Chat chat;
    @Column(columnDefinition = "TEXT")
    private String content;
    private UserRole userRole;
    private LocalDateTime timestamp;
}
class CustomChatMessageSerializer extends JsonSerializer<ChatMessages> {
    @Override
    public void serialize(ChatMessages chatMessages, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", chatMessages.getId());
     
        jsonGenerator.writeStringField("senderEmail", chatMessages.getSender().getEmail());
        jsonGenerator.writeStringField("receiverEmail", chatMessages.getReceiver().getEmail());
        jsonGenerator.writeStringField("content", chatMessages.getContent());
        jsonGenerator.writePOJOField("timeStamp", chatMessages.getTimestamp());
        jsonGenerator.writeStringField("photoUrlReceiver", chatMessages.getReceiver().getPhotoUrl());
        jsonGenerator.writeStringField("photoUrlSender", chatMessages.getSender().getPhotoUrl());
        jsonGenerator.writeEndObject();
    }
}
