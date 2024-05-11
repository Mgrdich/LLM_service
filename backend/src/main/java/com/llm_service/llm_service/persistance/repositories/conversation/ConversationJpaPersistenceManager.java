package com.llm_service.llm_service.persistance.repositories.conversation;

import com.llm_service.llm_service.dto.Conversation;
import com.llm_service.llm_service.dto.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationJpaPersistenceManager implements ConversationPersistenceManager {
    private final ConversationRepository conversationRepository;
    private final ConversationEntityMapper conversationEntityMapper;

    @Override
    public List<Conversation> findAll(UUID userId) {
        return conversationRepository.findAll().stream()
                .filter(conversationEntity -> conversationEntity.getUser().getId() == userId)
                .map(conversationEntityMapper::map)
                .toList();
    }

    @Override
    public Optional<Conversation> findById(UUID id, UUID userId) {
        return conversationRepository
                .findById(id)
                .filter(conversationEntity -> conversationEntity.getUser().getId() == userId)
                .map(conversationEntityMapper::map);
    }

    @Override
    public Conversation save(Conversation conversation, User user) {
        return conversationEntityMapper.map(
                conversationRepository.save(conversationEntityMapper.map(conversation, user)));
    }

    @Override
    public void delete(UUID id) {
        conversationRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        conversationRepository.deleteAll();
    }
}
