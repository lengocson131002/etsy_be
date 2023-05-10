package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Message.COLLECTION_NAME)
public class Message {
    public final static String COLLECTION_NAME = "messages";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private OffsetDateTime time;
    private Boolean isAdmin;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @ElementCollection
    @CollectionTable(
            name = "message_images",
            joinColumns = @JoinColumn(name = "message_id"))
    @Column(name = "image")
    private List<String> images;
}
