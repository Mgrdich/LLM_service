package com.llm_service.llm_service.persistance.repositories.admin;

import com.llm_service.llm_service.persistance.entities.AdminEntity;
import com.llm_service.llm_service.service.admin.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminEntityMapper {
    Admin map(AdminEntity adminEntity);

    AdminEntity map(Admin admin);
}
