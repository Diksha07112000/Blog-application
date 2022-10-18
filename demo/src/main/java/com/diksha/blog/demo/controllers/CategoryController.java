package com.diksha.blog.demo.controllers;

import com.diksha.blog.demo.payloads.ApiResponse;
import com.diksha.blog.demo.payloads.CategoryDto;
import com.diksha.blog.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        CategoryDto category = this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> allCategory = this.categoryService.getAllCategory();
        return ResponseEntity.ok(allCategory);
    }
}
