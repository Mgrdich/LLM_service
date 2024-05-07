package com.llm_service.llm_service.model;

import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Discussion {
    UUID id;
    String text;
    DiscussionRole promptRole;
    Conversation conversation;
}
