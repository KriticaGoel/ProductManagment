package com.kritica.service;

import com.kritica.exception.APIException;
import com.kritica.model.Category;
import com.kritica.payload.CategoryDTO;
import com.kritica.payload.CategoryResponse;
import com.kritica.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
//        if(categories.isEmpty()){
//            throw new APIException("No category created till now");
//        }
       // modelMapper
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategories(categoryDTOS);

        return categoryResponse;

    }

    @Override
    public String createNewCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        categoryRepository.save(category);
        return "Category created successfully";
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
