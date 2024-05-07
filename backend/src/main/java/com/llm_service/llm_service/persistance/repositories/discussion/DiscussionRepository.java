package com.llm_service.llm_service.persistance.repositories.discussion;

import com.llm_service.llm_service.persistance.entities.DiscussionEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionRepository extends JpaRepository<DiscussionEntity, UUID> {}
