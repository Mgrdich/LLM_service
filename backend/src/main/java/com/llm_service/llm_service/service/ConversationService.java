package com.llm_service.llm_service.service;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.Discussion;
import com.llm_service.llm_service.dto.User;
import com.llm_service.llm_service.exception.UnauthorizedException;
import com.llm_service.llm_service.persistance.entities.DiscussionRole;
import com.llm_service.llm_service.persistance.repositories.conversation.ConversationPersistenceManager;
import com.llm_service.llm_service.persistance.repositories.discussion.DiscussionPersistenceManager;
import com.llm_service.llm_service.service.user.UserContext;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final LLMService llmService;
    private final ConversationPersistenceManager conversationPersistenceManager;
    private final DiscussionPersistenceManager discussionPersistenceManager;
    private final UserContext userContext;

    public Conversation start() throws UnauthorizedException {
        Optional<User> user = userContext.getUserFromContext();

        if (user.isEmpty()) {
            throw new UnauthorizedException();
        }

        Conversation conversation = Conversation.builder().discussions(null).build();
        return conversationPersistenceManager.save(conversation, user.get());
    }

    public List<Conversation> getAll() throws UnauthorizedException {
        Optional<User> user = userContext.getUserFromContext();

        if (user.isEmpty()) {
            throw new UnauthorizedException();
        }

        return conversationPersistenceManager.findAll(user.get().getId());
    }

    public List<Conversation> getAll(Pageable pageable) throws UnauthorizedException {
        Optional<User> user = userContext.getUserFromContext();

        if (user.isEmpty()) {
            throw new UnauthorizedException();
        }

        UUID userId = user.get().getId();
        return conversationPersistenceManager.findAll(userId, pageable);
    }

    public Optional<Conversation> getByID(UUID id) throws UnauthorizedException {
        Optional<User> user = userContext.getUserFromContext();
        if (user.isEmpty()) {
            throw new UnauthorizedException();
        }

        return conversationPersistenceManager.findById(id, user.get().getId());
    }

    // TODO optimize this fetching mechanism
    public List<Discussion> askLlmQuestion(Conversation conversation, String text) throws UnauthorizedException {

        Optional<User> user = userContext.getUserFromContext();

        if (user.isEmpty()) {
            throw new UnauthorizedException();
        }

        Discussion discussionFromUserParam =
                Discussion.builder().promptRole(DiscussionRole.USER).text(text).build();

        Discussion discussionFromUser =
                discussionPersistenceManager.save(discussionFromUserParam, conversation, user.get());

        if (conversation.getDiscussions().isEmpty()) {
            String conversationTitle = discussionFromUser
                    .getText()
                    .substring(0, Math.min(20, discussionFromUser.getText().length()));
            conversationPersistenceManager.save(
                    conversation.toBuilder().title(conversationTitle).build(), user.get());
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
                discussionPersistenceManager.save(discussionFromAssistanceParam, conversation, user.get());

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

    public Conversation editTitle(Conversation conversation, String title) throws UnauthorizedException {
        Optional<User> user = userContext.getUserFromContext();

        if (user.isEmpty()) {
            throw new UnauthorizedException();
        }
        Conversation savedConversation = saveEditedTitle(conversation, title, user);

        return getConversation(conversation, savedConversation.getTitle());
    }

    private Conversation getConversation(Conversation conversation, String title) {
        conversation = Conversation.builder()
                .id(conversation.getId())
                .discussions(conversation.getDiscussions())
                .title(title)
                .createdOn(conversation.getCreatedOn())
                .lastUpdatedOn(Instant.now())
                .build();
        return conversation;
    }

    private Conversation saveEditedTitle(Conversation conversation, String title, Optional<User> user) {
        return conversationPersistenceManager.save(
                conversation.toBuilder().title(title).build(), user.get());
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
