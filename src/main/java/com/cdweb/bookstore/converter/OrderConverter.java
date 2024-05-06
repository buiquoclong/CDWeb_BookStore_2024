package com.cdweb.bookstore.converter;

import com.cdweb.bookstore.dto.OrderDTO;
import com.cdweb.bookstore.entities.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    @Autowired
    private ModelMapper modelMapper;

    public OrderEntity toEntity(OrderDTO dto) {
        return modelMapper.map(dto, OrderEntity.class);
    }

    public OrderDTO toDTO(OrderEntity entity) {
        return modelMapper.map(entity, OrderDTO.class);
    }
}
