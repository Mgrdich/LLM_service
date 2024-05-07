package com.llm_service.llm_service.controller.discussion;

import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
public class DiscussionRequest {
    String text;
    DiscussionRole promptRole;
    UUID conversation;
}
