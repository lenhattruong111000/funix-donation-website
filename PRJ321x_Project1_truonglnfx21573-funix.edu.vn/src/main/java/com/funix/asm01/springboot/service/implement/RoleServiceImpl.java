package com.funix.asm01.springboot.service.implement;

import com.funix.asm01.springboot.entity.Role;
import com.funix.asm01.springboot.repository.RoleRepository;
import com.funix.asm01.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role getRoleById(long id) {
        Optional<Role> optional =roleRepository.findById(id);
        Role role =null;

        if(optional.isPresent()) {
           role= optional.get();
        }else {
            throw new RuntimeException("The user role not found by id: "+id);
        }
        return role;
    }
}
