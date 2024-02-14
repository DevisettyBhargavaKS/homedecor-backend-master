package com.CodeWithBhargav.dto;

import com.CodeWithBhargav.model.Category;
import com.CodeWithBhargav.request.CategoryRequest;
import com.CodeWithBhargav.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDto {
    public CategoryResponse mapToCategoryResponse(List<Category> categories) {
        CategoryResponse categoryResponse = new CategoryResponse();

        ArrayList<CategoryRequest> categoryRequests = new ArrayList<>();
        for (Category category : categories) {
            categoryRequests.add(new CategoryRequest(category.getId(), category.getTitle()));
        }

        categoryResponse.setCategories(categoryRequests);
        return categoryResponse;
    }

    public Category mapToCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        if (categoryRequest.getId() != null) {
            category.setId(categoryRequest.getId());
        }
        category.setTitle(categoryRequest.getTitle());
        return category;
    }
}
