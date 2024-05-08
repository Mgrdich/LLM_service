package com.llm_service.llm_service.persistance.repositories.conversation;

import com.llm_service.llm_service.model.Conversation;
import com.llm_service.llm_service.persistance.entities.ConversationEntity;
import com.llm_service.llm_service.persistance.entities.DiscussionEntity;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConversationEntityMapper {
    Conversation map(ConversationEntity conversationEntity);

    ConversationEntity map(Conversation conversation);

    List<UUID> map(List<DiscussionEntity> discussionEntities);

    List<DiscussionEntity> mapToEntities(List<UUID> discussionIds);

    UUID map(DiscussionEntity discussionEntity);

    DiscussionEntity map(UUID value);
}
