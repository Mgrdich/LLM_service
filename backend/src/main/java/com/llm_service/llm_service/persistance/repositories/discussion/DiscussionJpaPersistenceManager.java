package com.llm_service.llm_service.persistance.repositories.discussion;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.Discussion;
import com.llm_service.llm_service.persistance.entities.DiscussionEntity;
import com.llm_service.llm_service.persistance.repositories.conversation.ConversationEntityMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscussionJpaPersistenceManager implements DiscussionPersistenceManager {
    private final DiscussionRepository discussionRepository;
    private final DiscussionEntityMapper discussionEntityMapper;
    private final ConversationEntityMapper conversationEntityMapper;

    @Override
    public List<Discussion> findAll() {
        return discussionRepository.findAll().stream()
                .map(discussionEntityMapper::map)
                .toList();
    }

    @Override
    public Optional<Discussion> findById(UUID id) {
        return discussionRepository.findById(id).map(discussionEntityMapper::map);
    }

    @Override
    public Discussion save(Discussion discussion, Conversation conversation) {
        DiscussionEntity discussionEntity = DiscussionEntity.builder()
                .text(discussion.getText())
                .promptRole(discussion.getPromptRole())
                .conversation(conversationEntityMapper.map(conversation))
                .build();
        return discussionEntityMapper.map(discussionRepository.save(discussionEntity));
    }

    @Override
    public void delete(UUID id) {
        discussionRepository.deleteById(id);
    }
}
