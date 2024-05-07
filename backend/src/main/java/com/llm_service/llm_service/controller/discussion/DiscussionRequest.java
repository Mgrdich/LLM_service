package com.llm_service.llm_service.controller.discussion;

import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class DiscussionRequest {
    String text;
    DiscussionRole promptRole;
    UUID conversation;
}
