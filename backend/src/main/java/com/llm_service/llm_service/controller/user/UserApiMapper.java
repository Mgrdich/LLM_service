package com.llm_service.llm_service.controller.user;

import com.llm_service.llm_service.service.jwt.AuthenticationResponse;
import com.llm_service.llm_service.service.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserApiMapper {
    LoginResponse map(AuthenticationResponse response);

    @Mapping(target = "name", source = "admin.user.username")
    UserResponse map(User user);
}
