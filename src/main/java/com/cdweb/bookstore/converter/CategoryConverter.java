package com.cdweb.bookstore.converter;

import com.cdweb.bookstore.dto.CategoryDTO;
import com.cdweb.bookstore.entities.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    @Autowired
    private ModelMapper modelMapper;

    //chuyển từ entity sang dto bằng modelmapper
    public CategoryDTO toDTO(CategoryEntity catEntity) {
        return modelMapper.map(catEntity, CategoryDTO.class);
    }

    //chuển từ dto sang entity
    public CategoryEntity toEntity(CategoryDTO catDTO) {
        return modelMapper.map(catDTO, CategoryEntity.class);
    }
}
