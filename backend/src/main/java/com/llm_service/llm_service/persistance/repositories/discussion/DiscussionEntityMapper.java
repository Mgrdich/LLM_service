package com.llm_service.llm_service.persistance.repositories.discussion;

import com.llm_service.llm_service.model.Discussion;
import com.llm_service.llm_service.persistance.entities.DiscussionEntity;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscussionEntityMapper {
    Discussion map(DiscussionEntity discussionEntity);

    DiscussionEntity map(Discussion discussion);

    List<UUID> map(List<DiscussionEntity> discussionEntities);

    UUID maptoId(DiscussionEntity discussionEntity);

    List<DiscussionEntity> mapToEntities(List<UUID> discussionIds);

    DiscussionEntity map(UUID value);
}
