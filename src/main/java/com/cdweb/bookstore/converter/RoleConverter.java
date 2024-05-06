package com.cdweb.bookstore.converter;

import com.cdweb.bookstore.dto.RoleDTO;
import com.cdweb.bookstore.entities.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    @Autowired
    private ModelMapper modelMapper;

    public RoleEntity toEntity(RoleDTO roleDto) {
        return modelMapper.map(roleDto,RoleEntity.class);
    }

    public RoleDTO toDTO(RoleEntity roleEntity) {
        return modelMapper.map(roleEntity, RoleDTO.class);
    }
}
