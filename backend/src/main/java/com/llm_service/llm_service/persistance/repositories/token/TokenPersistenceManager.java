package com.llm_service.llm_service.persistance.repositories.token;

import com.aua.flightreservationsystem.core.jwt.Token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenPersistenceManager {

    List<Token> findAll();

    List<Token> findAllTokensByUser(UUID id);

    Optional<Token> findById(UUID id);

    void save(Token token);

    void saveAll(List<Token> tokens);

    void delete(UUID id);
}
