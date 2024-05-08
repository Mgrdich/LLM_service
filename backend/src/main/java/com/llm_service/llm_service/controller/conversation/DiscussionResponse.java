package com.llm_service.llm_service.controller.conversation;

import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class DiscussionResponse {
    UUID id;
    String text;
    DiscussionRole promptRole;
}
