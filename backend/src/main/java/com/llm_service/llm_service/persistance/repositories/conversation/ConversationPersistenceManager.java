package com.llm_service.llm_service.persistance.repositories.conversation;

import com.llm_service.llm_service.model.Conversation;
import com.llm_service.llm_service.model.Discussion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversationPersistenceManager {
    List<Conversation> findAll();
    Optional<Conversation> findById(UUID id);
    Conversation save(Conversation conversation);
    void delete(UUID id);
}