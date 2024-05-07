package com.llm_service.llm_service.persistance.repositories.conversation;

import com.llm_service.llm_service.persistance.entities.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConversationRepository extends JpaRepository<ConversationEntity, UUID> {
}
