package com.cdweb.bookstore.service;

import com.cdweb.bookstore.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(int id);
    void save(CategoryDTO categoryDTO);
    void deleteByCatId(int id);
    void updateCat(CategoryDTO cat);
    List<CategoryDTO> findTenCat();
}
