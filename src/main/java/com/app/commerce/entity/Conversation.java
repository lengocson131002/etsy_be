package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

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

    private Integer unreadCount;

    private String messageTime;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
