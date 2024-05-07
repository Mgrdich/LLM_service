package com.llm_service.llm_service.controller.conversation;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ConversationResponse {
    UUID id;
    String text;
}
