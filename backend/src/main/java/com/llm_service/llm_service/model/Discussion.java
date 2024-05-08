package com.llm_service.llm_service.model;

import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class Discussion extends Base {
    UUID id;
    String text;
    DiscussionRole promptRole;
    Conversation conversation;
}
