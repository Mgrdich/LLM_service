package com.llm_service.llm_service.controller.user;

import com.llm_service.llm_service.dto.User;
import com.llm_service.llm_service.service.jwt.AuthenticationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserApiMapper {
    LoginResponse map(AuthenticationResponse response);

    @Mapping(target = "name", source = "username")
    UserResponse map(User user);
}
