package com.kritica.service;

import com.kritica.exception.APIException;
import com.kritica.model.Category;
import com.kritica.payload.CategoryDTO;
import com.kritica.payload.CategoryResponse;
import com.kritica.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(ModelMapper modelMapper,CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories=categoryRepository.findAll();

       // modelMapper
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategories(categoryDTOS);

        return categoryResponse;

    }

    @Override
    public CategoryDTO createNewCategory(@Valid CategoryDTO categoryDTO) {
        Category category=modelMapper.map(categoryDTO,Category.class);
        try {
            Category result = categoryRepository.save(category);
            return modelMapper.map(result,CategoryDTO.class);
        }catch (DataIntegrityViolationException e){
            throw new APIException("Category name "+categoryDTO.getName()+" already exists");
        }catch (IllegalArgumentException e) {
            throw new APIException("Invalid category data: " + e.getMessage());
        }catch (Exception e) {
            throw new APIException("Error creating category: " + e.getMessage());
        }
    }


    @Override
    public String updateCategory(Long id, CategoryDTO categoryDTO) {

        return null;
    }

    @Override
    public String deleteCategory(Long id) {

        return null;
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        return null;
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return null;
    }
}
