package com.app.commerce.mappings.impl;

import com.app.commerce.dto.conversation.response.ConversationResponse;
import com.app.commerce.dto.shop.request.ConversationDto;
import com.app.commerce.entity.Conversation;
import com.app.commerce.mappings.ConversationMapper;
import org.springframework.stereotype.Service;

@Service
public class ConversationMapperImpl implements ConversationMapper {
    @Override
    public Conversation toEntity(ConversationDto dto) {
        if (dto == null) {
            return null;
        }
        return new Conversation()
                .setCustomerName(dto.getCustomerName())
                .setUnreadCount(dto.getUnreadCount())
                .setMessageTime(dto.getMessageTime());
    }

    @Override
    public ConversationResponse toResponse(Conversation conversation) {
        if (conversation == null) {
            return null;
        }
        return new ConversationResponse()
                .setId(conversation.getId())
                .setCustomerName(conversation.getCustomerName())
                .setUnreadCount(conversation.getUnreadCount())
                .setMessageTime(conversation.getMessageTime());
    }
}
