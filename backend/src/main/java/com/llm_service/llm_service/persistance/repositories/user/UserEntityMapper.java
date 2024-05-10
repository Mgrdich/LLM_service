package com.llm_service.llm_service.persistance.repositories.user;

import com.aua.flightreservationsystem.core.admin.Admin;
import com.aua.flightreservationsystem.core.customer.Customer;
import com.aua.flightreservationsystem.core.employee.Employee;
import com.aua.flightreservationsystem.core.user.User;
import com.aua.flightreservationsystem.persistence.model.FullName;
import com.aua.flightreservationsystem.persistence.model.UserEntity;
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

    User map(Admin admin);

    User map(Customer customer);

    User map(Employee employee);
}
