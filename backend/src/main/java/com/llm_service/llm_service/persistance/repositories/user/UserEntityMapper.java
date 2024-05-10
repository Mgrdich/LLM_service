package com.llm_service.llm_service.persistance.repositories.user;

import com.llm_service.llm_service.dto.User;
import com.llm_service.llm_service.persistance.entities.FullName;
import com.llm_service.llm_service.persistance.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "firstName", source = "userEntity.fullName.firstName")
    @Mapping(target = "lastName", source = "userEntity.fullName.lastName")
    User map(UserEntity userEntity);

    FullName map(String firstName, String lastName);

    @Mapping(target = "fullName.firstName", source = "firstName")
    @Mapping(target = "fullName.lastName", source = "lastName")
    UserEntity map(User user);
}
