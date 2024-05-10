package com.llm_service.llm_service.controller.conversation;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ConversationResponseCompact {
    UUID id;
    List<UUID> discussions;
    String title;
    Date lastUpdatedOn;
    Date createdOn;
}
