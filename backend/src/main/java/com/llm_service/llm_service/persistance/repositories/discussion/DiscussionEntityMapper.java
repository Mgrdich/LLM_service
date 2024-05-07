package com.llm_service.llm_service.persistance.repositories.discussion;

import com.llm_service.llm_service.model.Discussion;
import com.llm_service.llm_service.persistance.entities.DiscussionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscussionEntityMapper {
    Discussion map(DiscussionEntity discussionEntity);

    DiscussionEntity map(Discussion discussion);
}
