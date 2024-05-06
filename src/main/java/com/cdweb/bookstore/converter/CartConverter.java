package com.cdweb.bookstore.converter;

import com.cdweb.bookstore.dto.CartDTO;
import com.cdweb.bookstore.entities.CartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CartEntity toEntity(CartDTO cartDto) {
        return modelMapper.map(cartDto, CartEntity.class);
    }

    public CartDTO toDTO(CartEntity cartEntity) {
        return modelMapper.map(cartEntity, CartDTO.class);
    }
}
