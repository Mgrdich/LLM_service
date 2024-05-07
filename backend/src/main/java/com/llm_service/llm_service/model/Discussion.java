package com.llm_service.llm_service.model;

import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Discussion {
    UUID id;
    String text;
    DiscussionRole promptRole;
    Conversation conversation;
}
