package com.app.commerce.mappings.impl;

import com.app.commerce.dto.conversation.response.ConversationDetailResponse;
import com.app.commerce.dto.conversation.response.ConversationResponse;
import com.app.commerce.dto.conversation.response.MessageResponse;
import com.app.commerce.dto.shop.request.ConversationDto;
import com.app.commerce.dto.shop.request.MessageDto;
import com.app.commerce.entity.Conversation;
import com.app.commerce.entity.Message;
import com.app.commerce.mappings.ConversationMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class ConversationMapperImpl implements ConversationMapper {
    @Override
    public Conversation toEntity(ConversationDto dto) {
        if (dto == null) {
            return null;
        }
        Conversation conversation =  new Conversation()
                .setCustomerName(dto.getCustomerName())
                .setCustomerImage(dto.getCustomerImage())
                .setUnreadCount(dto.getUnreadCount())
                .setMessageTime(dto.getMessageTime())
                .setLastMessageTime(dto.getLastMessageTime());

        if (dto.getMessages() != null) {
            conversation.setMessages(dto.getMessages()
                    .stream()
                    .map(this::toMessageEntity)
                    .collect(Collectors.toList()));
        }
        return conversation;
    }

    @Override
    public ConversationResponse toResponse(Conversation conversation) {
        if (conversation == null) {
            return null;
        }
        return new ConversationResponse()
                .setId(conversation.getId())
                .setCustomerName(conversation.getCustomerName())
                .setCustomerImage(conversation.getCustomerImage())
                .setUnreadCount(conversation.getUnreadCount())
                .setMessageTime(conversation.getMessageTime())
                .setLastMessageTime(conversation.getLastMessageTime())
                .setShopId(conversation.getShop() != null ? conversation.getShop().getId() : null)
                .setShopName(conversation.getShop() != null ? conversation.getShop().getName() : null);
    }

    @Override
    public ConversationDetailResponse toDetailResponse(Conversation conversation) {
        if (conversation == null) {
            return null;
        }
        ConversationDetailResponse response = new ConversationDetailResponse();
        response.setId(conversation.getId());
        response.setCustomerName(conversation.getCustomerName());
        response.setCustomerImage(conversation.getCustomerImage());
        response.setUnreadCount(conversation.getUnreadCount());
        response.setMessageTime(conversation.getMessageTime());
        response.setLastMessageTime(conversation.getLastMessageTime());
        response.setShopId(conversation.getShop() != null ? conversation.getShop().getId() : null);
        response.setShopName(conversation.getShop() != null ? conversation.getShop().getName() : null);

        if (conversation.getMessages() != null) {
            response.setMessages(conversation.getMessages()
                    .stream()
                    .sorted(Comparator.comparing(Message::getTime))
                    .map(this::toMessageResponse)
                    .collect(Collectors.toList())
            );
        }

        return response;
    }

    @Override
    public MessageResponse toMessageResponse(Message message) {
        if (message == null) {
            return null;
        }

        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setTime(message.getTime());
        response.setContent(message.getContent());
        response.setIsAdmin(message.getIsAdmin());
        response.setImages(message.getImages());
        return response;
    }

    @Override
    public Message toMessageEntity(MessageDto messageDto) {
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setTime(messageDto.getTime());
        message.setIsAdmin(messageDto.getIsAdmin());
        message.setImages(messageDto.getImages());
        return message;
    }


}
