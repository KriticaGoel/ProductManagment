package com.kritica.service;

import com.kritica.model.Category;
import com.kritica.payload.CategoryDTO;
import com.kritica.payload.CategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories();
    String createNewCategory(CategoryDTO categoryDTO);
    String updateCategory(Long id, CategoryDTO categoryDTO);
    String deleteCategory(Long id);
    CategoryResponse getCategoryByName(String name);
    CategoryResponse getCategoryById(Long id);

}
