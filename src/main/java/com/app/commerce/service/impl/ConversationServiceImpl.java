package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.conversation.request.GetAllConversationsRequest;
import com.app.commerce.dto.conversation.response.ConversationDetailResponse;
import com.app.commerce.dto.conversation.response.ConversationResponse;
import com.app.commerce.entity.Conversation;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.ConversationMapper;
import com.app.commerce.repository.ConversationRepository;
import com.app.commerce.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationMapper conversationMapper;

    @Override
    public PageResponse<Conversation, ConversationResponse> getAllConversations(GetAllConversationsRequest request) {
        Page<Conversation> pageResult = conversationRepository.findAll(request.getSpecification(), request.getPageable());
        return new PageResponse<>(pageResult, conversationMapper::toResponse);
    }

    @Override
    public ConversationDetailResponse getConversation(Long id) {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.CONVERSATION_ERROR_NOT_FOUND));

        return conversationMapper.toDetailResponse(conversation);
    }
}
