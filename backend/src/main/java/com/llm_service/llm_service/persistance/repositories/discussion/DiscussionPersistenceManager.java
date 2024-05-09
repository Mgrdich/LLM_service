package com.llm_service.llm_service.persistance.repositories.discussion;

import com.llm_service.llm_service.model.Conversation;
import com.llm_service.llm_service.model.Discussion;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiscussionPersistenceManager {
    List<Discussion> findAll();

    Optional<Discussion> findById(UUID id);

    Discussion save(Discussion discussion, Conversation conversation);

    void delete(UUID id);
}
