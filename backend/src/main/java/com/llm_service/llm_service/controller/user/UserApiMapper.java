package com.llm_service.llm_service.controller.user;

import com.llm_service.llm_service.service.admin.Admin;
import com.llm_service.llm_service.service.customer.Customer;
import com.llm_service.llm_service.service.jwt.AuthenticationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserApiMapper {
    LoginResponse map(AuthenticationResponse response);

    @Mapping(target = "name", source = "admin.user.username")
    UserResponse map(Admin admin);


    @Mapping(target = "name", source = "customer.user.username")
    UserResponse map(Customer customer);
}
