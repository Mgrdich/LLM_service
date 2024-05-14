package com.llm_service.llm_service.persistance.repositories.conversation;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface ConversationPersistenceManager {
    List<Conversation> findAll(UUID userId);

    List<Conversation> findAll(UUID userId, Pageable pageable);

    Optional<Conversation> findById(UUID id, UUID userId);

    Conversation save(Conversation conversation, User user);

    void delete(UUID id);

    void deleteAll();
}
