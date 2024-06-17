package com.cdweb.bookstore.service.impl;

import com.cdweb.bookstore.converter.RoleConverter;
import com.cdweb.bookstore.dto.RoleDTO;
import com.cdweb.bookstore.repository.RoleRepository;
import com.cdweb.bookstore.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleConverter roleConverter;


    @Override
    public RoleDTO findRolebyName(String name) {
        return roleConverter.toDTO(roleRepository.findByName(name));
    }
}
