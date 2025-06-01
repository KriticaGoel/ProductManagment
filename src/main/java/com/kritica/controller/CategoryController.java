package com.kritica.controller;

import com.kritica.exception.APIException;
import com.kritica.payload.CategoryDTO;
import com.kritica.payload.CategoryResponse;
import com.kritica.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    //Get all Category
    @GetMapping("/")
    public ResponseEntity<CategoryResponse> getAllCategories(){
       CategoryResponse response= categoryService.getAllCategories();
       if(response.getCategories().isEmpty()){
           return ResponseEntity.noContent().build();
       }
        return ResponseEntity.ok(response);
    }
    //Create a new Category
    //the validation will be triggered when the DTO is received by the controller ( on controller parameter) `@Valid`
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createNewCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO response = categoryService.createNewCategory(categoryDTO);
        return ResponseEntity.ok(response);
    }

    //Update Existing Category
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(
                "Category Updated Successfully",
                HttpStatus.OK
        );
    }

    //Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
    return new ResponseEntity<>("Category deleted successfully",HttpStatus.OK);
    }
    //get Category by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CategoryDTO>> getCategoryByName(@PathVariable String name){
        return null;
    }

    //get Category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        return null;
    }
}
