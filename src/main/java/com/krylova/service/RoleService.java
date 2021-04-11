package com.krylova.service;

import com.krylova.entity.Role;
import com.krylova.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void create(Role role){
        roleRepository.save(role);
    }

    public void update(Role role) { roleRepository.save(role); }

    public void delete(Role role) { roleRepository.delete(role); }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Optional<Role> find(Long id){
        return roleRepository.findById(id);
    }
}
