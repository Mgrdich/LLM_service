package com.llm_service.llm_service.controller.discussion;

import com.llm_service.llm_service.model.Conversation;
import com.llm_service.llm_service.model.Discussion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscussionControllerMapper {
    DiscussionResponse map(Discussion Discussion);
}
