package com.llm_service.llm_service.persistance.repositories.token;

import com.aua.flightreservationsystem.core.jwt.Token;
import com.aua.flightreservationsystem.persistence.model.TokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenEntityMapper {
    Token map(TokenEntity tokenEntity);

    TokenEntity map(Token token);
}
