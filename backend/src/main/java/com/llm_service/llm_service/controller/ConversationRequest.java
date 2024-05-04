package com.llm_service.llm_service.controller;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ConversationRequest {
    String text;
}
