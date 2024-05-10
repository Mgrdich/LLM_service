package com.llm_service.llm_service.persistance.repositories.token;

import com.aua.flightreservationsystem.persistence.model.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {

    List<TokenEntity> findAllTokensByUserId(UUID id);

    Optional<TokenEntity> findByToken(String token);
}
