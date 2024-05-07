package com.llm_service.llm_service.persistance.repositories.conversation;

import com.llm_service.llm_service.model.Conversation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversationJpaPersistenceManager implements ConversationPersistenceManager {
    private final ConversationRepository conversationRepository;
    private final ConversationEntityMapper conversationEntityMapper;

    @Override
    public List<Conversation> findAll() {
        return conversationRepository.findAll().stream()
                .map(conversationEntityMapper::map)
                .toList();
    }

    @Override
    public Optional<Conversation> findById(UUID id) {
        return conversationRepository.findById(id).map(conversationEntityMapper::map);
    }

    @Override
    public Conversation save(Conversation conversation) {
        return conversationEntityMapper.map(conversationRepository.save(conversationEntityMapper.map(conversation)));
    }

    @Override
    public void delete(UUID id) {
    conversationRepository.deleteById(id);
    }
}
