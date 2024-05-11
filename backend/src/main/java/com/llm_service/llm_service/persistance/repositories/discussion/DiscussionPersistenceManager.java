package com.llm_service.llm_service.persistance.repositories.discussion;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.Discussion;
import com.llm_service.llm_service.dto.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiscussionPersistenceManager {
    List<Discussion> findAll();

    Optional<Discussion> findById(UUID id);

    Discussion save(Discussion discussion, Conversation conversation, User user);

    void delete(UUID id);
}
