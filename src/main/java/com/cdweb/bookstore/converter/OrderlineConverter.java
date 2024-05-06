package com.cdweb.bookstore.converter;

import com.cdweb.bookstore.dto.OrderlineDTO;
import com.cdweb.bookstore.entities.OrderLineEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderlineConverter {
    @Autowired
    private ModelMapper modelMapper;

    public OrderLineEntity toEntity(OrderlineDTO dto) {
        return modelMapper.map(dto, OrderLineEntity.class);
    }

    public OrderlineDTO toDTO(OrderLineEntity entity) {
        return modelMapper.map(entity, OrderlineDTO.class);
    }
}
