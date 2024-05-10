package com.llm_service.llm_service.controller.user;

import com.aua.flightreservationsystem.core.admin.Admin;
import com.aua.flightreservationsystem.core.customer.Customer;
import com.aua.flightreservationsystem.core.employee.Employee;
import com.aua.flightreservationsystem.core.jwt.AuthenticationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserApiMapper {
    LoginResponse map(AuthenticationResponse response);

    @Mapping(target = "name", source = "admin.user.username")
    UserResponse map(Admin admin);

    @Mapping(target = "name", source = "employee.user.username")
    UserResponse map(Employee employee);

    @Mapping(target = "name", source = "customer.user.username")
    UserResponse map(Customer customer);
}
