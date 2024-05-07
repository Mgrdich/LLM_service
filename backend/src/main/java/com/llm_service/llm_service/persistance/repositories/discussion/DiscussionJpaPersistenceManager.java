package com.llm_service.llm_service.persistance.repositories.discussion;

import com.llm_service.llm_service.model.Discussion;
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
    public Discussion save(Discussion discussion) {
        return discussionEntityMapper.map(discussionRepository.save(discussionEntityMapper.map(discussion)));
    }

    @Override
    public void delete(UUID id) {
        discussionRepository.deleteById(id);
    }
}
