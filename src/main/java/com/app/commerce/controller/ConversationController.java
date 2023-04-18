package com.app.commerce.controller;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.conversation.request.GetAllConversationsRequest;
import com.app.commerce.dto.conversation.response.ConversationResponse;
import com.app.commerce.entity.Conversation;
import com.app.commerce.service.ConversationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping
    public ResponseEntity<PageResponse<Conversation, ConversationResponse>> getAllConversations(@Valid @ParameterObject GetAllConversationsRequest request) {
        PageResponse<Conversation, ConversationResponse> response = conversationService.getAllConversations(request);
        return ResponseEntity.ok(response);
    }
}
