package com.llm_service.llm_service.persistance.repositories.conversation;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.User;
import com.llm_service.llm_service.persistance.entities.ConversationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConversationEntityMapper {

    Conversation map(ConversationEntity conversationEntity);

    @Mapping(source = "conversation.id", target = "id")
    ConversationEntity map(Conversation conversation, User user);
}
