package com.llm_service.llm_service.model;

import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder(toBuilder = true)
public class Discussion extends Base {
    UUID id;
    String text;
    DiscussionRole promptRole;
    Conversation conversation;
}
