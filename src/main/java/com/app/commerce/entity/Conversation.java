package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Conversation.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@FieldNameConstants
public class Conversation {

    public static final String COLLECTION_NAME = "conversations";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String customerName;
    private String customerImage;

    private Integer unreadCount;

    private String messageTime;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "conversation"
    )
    private List<Message> messages;

    public Conversation setMessages(List<Message> messages) {
        if (this.messages == null) {
            this.messages = messages;
        } else {
            this.messages.clear();
            this.messages.addAll(messages);
        }

        this.messages.forEach(message -> message.setConversation(this));
        return this;
    }

}
