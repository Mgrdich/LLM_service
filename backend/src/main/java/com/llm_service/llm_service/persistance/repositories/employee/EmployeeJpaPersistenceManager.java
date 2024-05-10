package com.llm_service.llm_service.persistance.repositories.employee;

import com.aua.flightreservationsystem.core.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeJpaPersistenceManager implements EmployeePersistenceManager {
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityMapper employeeEntityMapper;

    @Autowired
    public EmployeeJpaPersistenceManager(
            EmployeeRepository employeeRepository, EmployeeEntityMapper employeeEntityMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeEntityMapper = employeeEntityMapper;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeEntityMapper::map)
                .toList();
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return employeeRepository.findById(id).map(employeeEntityMapper::map);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeEntityMapper.map(employeeRepository.save(employeeEntityMapper.map(employee)));
    }

    @Override
    public void delete(UUID id) {
        employeeRepository.deleteById(id);
    }
}
