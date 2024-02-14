package com.CodeWithBhargav.service;

import com.CodeWithBhargav.dto.CategoryDto;
import com.CodeWithBhargav.model.Category;
import com.CodeWithBhargav.repository.CategoryRepository;
import com.CodeWithBhargav.request.CategoryRequest;
import com.CodeWithBhargav.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryDto categoryDto;

    public CategoryResponse findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryDto.mapToCategoryResponse(categories);
    }

    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = categoryDto.mapToCategory(categoryRequest);
        categoryRepository.save(category);
        return findAll();
    }

    public CategoryResponse update(CategoryRequest categoryRequest) {
        Category category = categoryDto.mapToCategory(categoryRequest);
        categoryRepository.save(category);
        return findAll();
    }

    public CategoryResponse deleteById(Integer id) {
        categoryRepository.deleteById(Long.valueOf(id));
        return findAll();
    }
}
