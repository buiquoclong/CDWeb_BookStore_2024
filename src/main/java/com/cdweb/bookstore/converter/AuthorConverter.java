package com.cdweb.bookstore.converter;

import com.cdweb.bookstore.dto.AuthorDTO;
import com.cdweb.bookstore.entities.AuthorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AuthorEntity toEntity(AuthorDTO dto) {
        return modelMapper.map(dto, AuthorEntity.class);
    }

    public AuthorDTO toDTO(AuthorEntity entity) {
        return modelMapper.map(entity, AuthorDTO.class);
    }
}
