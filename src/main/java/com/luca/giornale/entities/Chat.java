package com.luca.giornale.entities;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "chats")
@JsonSerialize(using = CustomChatSerializer.class)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "email")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "email")
    private User receiver;

    private String chatName;

    @OneToMany(mappedBy = "chat")
    private List<ChatMessages> messages;
}

class CustomChatSerializer extends JsonSerializer<Chat> {
    @Override
    public void serialize(Chat chat, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        // Serialize the chat properties you want to include
        jsonGenerator.writeNumberField("id", chat.getId());
        jsonGenerator.writeStringField("chatName", chat.getChatName());

        // Serialize sender and receiver email (you can add more fields as needed)
        jsonGenerator.writeStringField("senderEmail", chat.getSender().getEmail());
        jsonGenerator.writeStringField("receiverEmail", chat.getReceiver().getEmail());

        // Serialize messages (assuming messages is a list of ChatMessages)
        if (chat.getMessages() != null) {
            jsonGenerator.writeArrayFieldStart("messages");
            for (ChatMessages message : chat.getMessages()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("messageId", message.getId());
                jsonGenerator.writeStringField("content", message.getContent());
                // Add more fields from ChatMessages as needed
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        } else {
            // If messages are null, you can handle it here, e.g., leave it empty
            jsonGenerator.writeArrayFieldStart("messages");
            jsonGenerator.writeEndArray();
        }

        jsonGenerator.writeEndObject();
    }
}
