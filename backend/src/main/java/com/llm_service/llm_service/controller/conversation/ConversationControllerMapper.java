package com.llm_service.llm_service.controller.conversation;

import com.llm_service.llm_service.model.Conversation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConversationControllerMapper {
    ConversationResponse map(Conversation conversation);
}
