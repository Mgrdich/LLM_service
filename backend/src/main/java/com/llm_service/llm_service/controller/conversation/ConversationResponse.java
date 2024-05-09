package com.llm_service.llm_service.controller.conversation;

import com.llm_service.llm_service.model.Discussion;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

// TODO dates part can be generalized in a separate class
@Value
@Builder
@Jacksonized
public class ConversationResponse {
    UUID id;
    List<Discussion> discussions;
    String title;
    Date lastUpdatedOn;
    Date createdOn;
}
