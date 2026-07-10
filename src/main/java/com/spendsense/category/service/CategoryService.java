package com.spendsense.category.service;

import com.spendsense.category.dto.CategoryRequest;
import com.spendsense.category.dto.CategoryResponse;
import com.spendsense.category.dto.CategoryUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getCategories();

    CategoryResponse getCategoryById(UUID categoryId);

    CategoryResponse updateCategory(
            UUID categoryId,
            CategoryUpdateRequest request
    );

    void deleteCategory(UUID categoryId);
}