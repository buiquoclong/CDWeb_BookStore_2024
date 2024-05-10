package com.cdweb.bookstore.controller.web;

import com.cdweb.bookstore.dto.CategoryDTO;
import com.cdweb.bookstore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService catServiceImp;
    @GetMapping("/getAllCat")
    public List<CategoryDTO> findAll() {
        return catServiceImp.findAll();
    }
    @GetMapping("/getCategory")
    public List<CategoryDTO> getTenCat() {
        return catServiceImp.findTenCat();
    }

}
