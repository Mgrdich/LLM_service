package com.llm_service.llm_service.persistance.repositories.conversation;

import com.llm_service.llm_service.persistance.entities.ConversationEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<ConversationEntity, UUID> {}
