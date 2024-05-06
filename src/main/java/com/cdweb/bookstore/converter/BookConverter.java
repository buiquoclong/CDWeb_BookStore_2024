package com.cdweb.bookstore.converter;

import com.cdweb.bookstore.dto.BookDTO;
import com.cdweb.bookstore.entities.BookEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BookEntity toEntity(BookDTO bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }

    public BookDTO toDTO(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    public BookEntity fromDtoToEntity(BookDTO dto, BookEntity entity) {
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDiscountPercent(dto.getDiscountPercent());
        entity.setPrice(dto.getPrice());
        entity.setTotal_page(dto.getTotal_page());
        entity.setPublisher(dto.getPublisher());
        entity.setYear_public(dto.getYear_public());
        entity.setQuantitySold(dto.getQuantitySold());
        entity.setActive(dto.isActive());
        entity.setHot(dto.isHot());
        entity.setNews(dto.isNews());
        return entity;
    }
}
