package com.llm_service.llm_service.service;

import com.llm_service.llm_service.controller.ConversationRequest;
import com.llm_service.llm_service.exception.conversation.ConversationNotFound;
import com.llm_service.llm_service.model.Conversation;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationService {
    LLMService llmService;

    public Conversation start() {
        return null;
    }

    public Conversation getByID(UUID id) throws ConversationNotFound {
        return null;
    }

    public Conversation update(UUID id, ConversationRequest conversationRequest) throws ConversationNotFound {
        return Conversation.builder()
                .id(id)
                .text(getPrediction(id, conversationRequest.getText()))
                .build();
    }

    private String getPrediction(UUID id, String text) {
        return llmService.generate(text).toString();
    }
}
