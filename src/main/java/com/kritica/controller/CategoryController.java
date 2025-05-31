package com.kritica.controller;

import com.kritica.payload.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    //Get all Category
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){

        return null;
    }
    //Create new Category
    @PostMapping("/")
    public ResponseEntity<String> createNewCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>("Category Created Successfully", HttpStatus.CREATED
        );
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
