package com.llm_service.llm_service.controller.conversation;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.Discussion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConversationApiMapper {

    @Mapping(target = "title", source = "title", defaultValue = "Untitled")
    @Mapping(target = "discussions", source = "discussions")
    ConversationResponse map(Conversation conversation);

    @Mapping(target = "title", source = "title", defaultValue = "Untitled")
    @Mapping(target = "discussions", source = "discussions")
    ConversationResponseCompact mapList(Conversation conversation);

    default List<Discussion> defaultDiscussions() {
        return new ArrayList<>();
    }

    default List<UUID> defaultDiscussionsCompact() {
        return new ArrayList<>();
    }

    DiscussionResponse map(Discussion discussion);

    default List<UUID> discussionsToListUUID(List<Discussion> discussions) {
        return discussions.stream().map(Discussion::getId).toList();
    }

    default List<DiscussionResponse> map(List<Discussion> discussions) {
        return discussions.stream()
                .map(discussion -> DiscussionResponse.builder()
                        .id(discussion.getId())
                        .promptRole(discussion.getPromptRole())
                        .text(discussion.getText())
                        .build())
                .toList();
    }
}
