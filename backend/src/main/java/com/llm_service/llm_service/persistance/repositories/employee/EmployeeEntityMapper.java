package com.llm_service.llm_service.persistance.repositories.employee;

import com.aua.flightreservationsystem.core.employee.Employee;
import com.aua.flightreservationsystem.persistence.model.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeEntityMapper {
    Employee map(EmployeeEntity employeeEntity);

    EmployeeEntity map(Employee employee);
}
