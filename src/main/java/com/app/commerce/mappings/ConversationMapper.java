package com.app.commerce.mappings;

import com.app.commerce.dto.conversation.response.ConversationResponse;
import com.app.commerce.dto.shop.request.ConversationDto;
import com.app.commerce.entity.Conversation;

public interface ConversationMapper {
    Conversation toEntity(ConversationDto dto);
    ConversationResponse toResponse(Conversation conversation);
}
