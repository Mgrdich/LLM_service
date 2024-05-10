package com.llm_service.llm_service.persistance.repositories.token;

import com.llm_service.llm_service.persistance.entities.TokenEntity;
import com.llm_service.llm_service.service.jwt.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenEntityMapper {
    Token map(TokenEntity tokenEntity);

    TokenEntity map(Token token);
}
