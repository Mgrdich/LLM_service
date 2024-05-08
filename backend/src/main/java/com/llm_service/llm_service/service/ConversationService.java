package com.llm_service.llm_service.service;

import com.llm_service.llm_service.controller.conversation.ConversationRequest;
import com.llm_service.llm_service.exception.conversation.ConversationNotFoundException;
import com.llm_service.llm_service.model.Conversation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.llm_service.llm_service.persistance.repositories.conversation.ConversationPersistenceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final LLMService llmService;
    private final ConversationPersistenceManager conversationPersistenceManager;

    public Conversation start() {
        return null;
    }

    public List<Conversation> getAll() {
        return conversationPersistenceManager.findAll();
    }

    public Optional<Conversation> getByID(UUID id) throws ConversationNotFoundException {
        return conversationPersistenceManager.findById(id);
    }

    public Conversation update(UUID id, ConversationRequest conversationRequest) throws ConversationNotFoundException {
        return Conversation.builder()
                .id(id)
                .text(getPrediction(id, conversationRequest.getText()))
                .build();
    }

    private String getPrediction(UUID id, String text) {
        return llmService.generate(text).toString();
    }
}
