package com.kritica.service;

import com.kritica.exception.APIException;
import com.kritica.model.Category;
import com.kritica.payload.CategoryDTO;
import com.kritica.payload.CategoryResponse;
import com.kritica.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.StaleObjectStateException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.xml.catalog.Catalog;
import java.util.List;

@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

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
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        try {
            Category updatedCategory = modelMapper.map(categoryDTO, Category.class);
            updatedCategory.setId(id);
            Category savedCategory = categoryRepository.save(updatedCategory);
            return modelMapper.map(savedCategory, CategoryDTO.class);
        }catch(RuntimeException e){
            throw new APIException("Error updating category: " + e.getMessage());
        }
    }

    @Transactional
    public CategoryDTO updateCategoryv2(Long id, @Valid CategoryDTO categoryDTO) {


        String newName = categoryDTO.getName().trim();
        if (newName.isEmpty()) {
            throw new APIException("Category name cannot be empty");
        }

        try {
            return categoryRepository.findById(id)
                    .map(existingCategory -> {
                        // Don't update if nothing changed
                        if (existingCategory.getName().equals(newName)) {
                          //  log.debug("No changes detected for category {}", id);
                            return modelMapper.map(existingCategory, CategoryDTO.class);
                        }

                        existingCategory.setName(newName);
                       // log.info("Successfully updated category {} with new name: {}", id, newName);
                        // No explicit save() needed due to @Transactional
                        return modelMapper.map(existingCategory, CategoryDTO.class);
                    })
                    .orElseThrow(() -> {
                       // log.error("Category not found with ID: {}", id);
                        return new APIException(String.format("Category with ID %d not found", id));
                    });

        } catch (DataIntegrityViolationException e) {
            //log.error("Failed to update category {} due to duplicate name: {}", id, newName);
            throw new APIException(String.format("Category with name '%s' already exists", newName));
        } catch (Exception e) {
          //  log.error("Unexpected error while updating category {}: {}", id, e.getMessage());
            throw new APIException("Error updating category: " + e.getMessage());
        }
    }


    @Override
    public CategoryDTO deleteCategory(Long id) {
        if(id==null){
            throw new APIException("Category id cannot be null");
        }
        try{
            Category category= categoryRepository.findById(id).orElseThrow(()->new APIException("Category with id "+id+" not found"));
            categoryRepository.deleteById(id);
            return modelMapper.map(category,CategoryDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
