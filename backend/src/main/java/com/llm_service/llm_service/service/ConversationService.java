package com.llm_service.llm_service.service;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.Discussion;
import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import com.llm_service.llm_service.persistance.repositories.conversation.ConversationPersistenceManager;
import com.llm_service.llm_service.persistance.repositories.discussion.DiscussionPersistenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final LLMService llmService;
    private final ConversationPersistenceManager conversationPersistenceManager;
    private final DiscussionPersistenceManager discussionPersistenceManager;

    public Conversation start() {
        Conversation conversation = Conversation.builder().discussions(null).build();
        return conversationPersistenceManager.save(conversation);
    }

    public List<Conversation> getAll() {
        return conversationPersistenceManager.findAll();
    }

    public Optional<Conversation> getByID(UUID id) {
        return conversationPersistenceManager.findById(id);
    }

    // TODO optimize this fetching mechanism
    public List<Discussion> askLlmQuestion(Conversation conversation, String text) {
        Discussion discussionFromUserParam =
                Discussion.builder().promptRole(DiscussionRole.USER).text(text).build();

        Discussion discussionFromUser = discussionPersistenceManager.save(discussionFromUserParam, conversation);

        if (conversation.getDiscussions().isEmpty()) {
            String conversationTitle = discussionFromUser
                    .getText()
                    .substring(0, Math.min(20, discussionFromUser.getText().length()));
            conversationPersistenceManager.save(
                    conversation.toBuilder().title(conversationTitle).build());
        }

        String prompt = initializeModel();
        prompt += preprocessPrompt(conversation.getDiscussions());
        prompt += text + LLMService.USERINST;
        String resultFromAssistant = getPrediction(prompt);

        Discussion discussionFromAssistanceParam = Discussion.builder()
                .promptRole(DiscussionRole.ASSISTANT)
                .text(resultFromAssistant)
                .build();

        Discussion discussionFromAssistance =
                discussionPersistenceManager.save(discussionFromAssistanceParam, conversation);

        List<Discussion> newDiscussions = new ArrayList<>();

        newDiscussions.add(discussionFromUser);
        newDiscussions.add(discussionFromAssistance);

        return newDiscussions;
    }

    public void delete(UUID id) {
        conversationPersistenceManager.delete(id);
    }

    public void deleteAll() {
        conversationPersistenceManager.deleteAll();
    }

    public void editTitle(Conversation conversation, String title) {
        conversationPersistenceManager.save(
                conversation.toBuilder().title(title).build());
    }

    private String getPrediction(String text) {
        return llmService.generateFullResponse(text);
    }

    private String initializeModel() {
        return llmService.initializeModelSystem();
    }

    private String preprocessPrompt(List<Discussion> discussions) {
        return llmService.preprocessPrompt(discussions);
    }
}
