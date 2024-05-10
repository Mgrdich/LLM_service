package com.llm_service.llm_service.persistance.repositories.customer;

import com.llm_service.llm_service.persistance.entities.CustomerEntity;
import com.llm_service.llm_service.service.customer.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {
    Customer map(CustomerEntity customerEntity);

    CustomerEntity map(Customer customer);
}
