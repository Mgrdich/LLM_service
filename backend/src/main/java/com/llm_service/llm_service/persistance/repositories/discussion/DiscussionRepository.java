package com.llm_service.llm_service.persistance.repositories.discussion;

import com.llm_service.llm_service.model.Discussion;
import com.llm_service.llm_service.persistance.entities.DiscussionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiscussionRepository extends JpaRepository<DiscussionEntity, UUID> {
}
