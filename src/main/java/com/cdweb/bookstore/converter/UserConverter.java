package com.cdweb.bookstore.converter;

import com.cdweb.bookstore.dto.UserDTO;
import com.cdweb.bookstore.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserEntity toEntity(UserDTO userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public UserDTO toDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
