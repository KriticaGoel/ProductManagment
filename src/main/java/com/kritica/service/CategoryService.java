package com.kritica.service;

import com.kritica.model.Category;
import com.kritica.payload.CategoryDTO;
import com.kritica.payload.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories();
    CategoryDTO createNewCategory(@Valid CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long id);
    CategoryDTO getCategoryByName(String name);
    CategoryDTO getCategoryById(Long id);

}
