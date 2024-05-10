package com.llm_service.llm_service.persistance.repositories.token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.llm_service.llm_service.service.jwt.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenJpaPersistenceManager implements TokenPersistenceManager {

    private final TokenRepository tokenRepository;
    private final TokenEntityMapper tokenEntityMapper;

    @Autowired
    public TokenJpaPersistenceManager(TokenRepository tokenRepository, TokenEntityMapper tokenEntityMapper) {
        this.tokenRepository = tokenRepository;
        this.tokenEntityMapper = tokenEntityMapper;
    }

    @Override
    public List<Token> findAll() {
        return tokenRepository.findAll().stream().map(tokenEntityMapper::map).toList();
    }

    @Override
    public List<Token> findAllTokensByUser(UUID id) {
        return tokenRepository.findAllTokensByUserId(id).stream()
                .map(tokenEntityMapper::map)
                .toList();
    }

    @Override
    public Optional<Token> findById(UUID id) {
        return tokenRepository.findById(id).map(tokenEntityMapper::map);
    }

    @Override
    public void save(Token token) {
        tokenRepository.save(tokenEntityMapper.map(token));
    }

    @Override
    public void saveAll(List<Token> tokens) {}

    @Override
    public void delete(UUID id) {
        tokenRepository.deleteById(id);
    }
}
