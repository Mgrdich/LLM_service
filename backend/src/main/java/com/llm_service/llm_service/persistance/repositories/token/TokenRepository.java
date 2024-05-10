package com.llm_service.llm_service.persistance.repositories.token;

import com.llm_service.llm_service.persistance.entities.TokenEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {

    List<TokenEntity> findAllTokensByUserId(UUID id);

    Optional<TokenEntity> findByToken(String token);
}
