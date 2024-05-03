package com.llm_service.llm_service.persistance.repositories.conversation;

import com.llm_service.llm_service.model.Conversation;
import com.llm_service.llm_service.persistance.entities.ConversationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConversationEntityMapper {
    Conversation map(ConversationEntity conversationEntity);

    ConversationEntity map(Conversation conversation);
}
