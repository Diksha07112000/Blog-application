package com.diksha.blog.demo.services.impl;

import com.diksha.blog.demo.entities.Category;
import com.diksha.blog.demo.exceptions.ResourceNotFoundException;
import com.diksha.blog.demo.payloads.CategoryDto;
import com.diksha.blog.demo.repositories.CategoryRepo;
import com.diksha.blog.demo.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category createdCategory = this.categoryRepo.save(cat);
        return this.modelMapper.map(createdCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> allCategories = this.categoryRepo.findAll();
        List<CategoryDto> allCategoriesDto = allCategories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return allCategoriesDto;
    }
}
