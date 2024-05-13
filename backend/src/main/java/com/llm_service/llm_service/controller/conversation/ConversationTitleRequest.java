package com.llm_service.llm_service.controller.conversation;

import com.llm_service.llm_service.constraint.ValidTitle;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ConversationTitleRequest {
    @NonNull
    @ValidTitle
    String title;
}
