package com.llm_service.llm_service.persistance.repositories.admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.llm_service.llm_service.service.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminJpaPersistenceManager implements AdminPersistenceManager {
    private final AdminRepository adminRepository;
    private final AdminEntityMapper adminEntityMapper;

    @Autowired
    public AdminJpaPersistenceManager(AdminRepository adminRepository, AdminEntityMapper adminEntityMapper) {
        this.adminRepository = adminRepository;
        this.adminEntityMapper = adminEntityMapper;
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll().stream().map(adminEntityMapper::map).toList();
    }

    @Override
    public Optional<Admin> findById(UUID id) {
        return adminRepository.findById(id).map(adminEntityMapper::map);
    }

    @Override
    public Admin save(Admin admin) {
        return adminEntityMapper.map(adminRepository.save(adminEntityMapper.map(admin)));
    }

    @Override
    public void delete(UUID id) {
        adminRepository.deleteById(id);
    }
}
