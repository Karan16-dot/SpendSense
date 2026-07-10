package com.spendsense.category.service;

import com.spendsense.category.dto.CreateCategoryRequest;
import com.spendsense.category.dto.UpdateCategoryRequest;
import com.spendsense.category.dto.CategoryResponse;
import com.spendsense.category.entity.Category;
import com.spendsense.category.mapper.CategoryMapper;
import com.spendsense.category.repository.CategoryRepository;
import com.spendsense.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CreateCategoryRequest request) {

        if (categoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new RuntimeException("Category already exists");
        }

        Category category = categoryMapper.toEntity(request);

        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    public CategoryResponse getCategoryById(UUID id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        return categoryMapper.toResponse(category);
    }

    public CategoryResponse updateCategory(
            UUID id,
            UpdateCategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        categoryMapper.updateCategory(request, category);
        category.setUpdatedAt(LocalDateTime.now());

        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    public void deleteCategory(UUID id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        if (Boolean.TRUE.equals(category.getIsDefault())) {
            throw new RuntimeException("Default categories cannot be deleted");
        }

        categoryRepository.delete(category);
    }
}