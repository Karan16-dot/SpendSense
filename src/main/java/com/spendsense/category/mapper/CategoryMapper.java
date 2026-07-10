package com.spendsense.category.mapper;

import com.spendsense.category.dto.CreateCategoryRequest;
import com.spendsense.category.dto.CategoryResponse;
import com.spendsense.category.dto.UpdateCategoryRequest;
import com.spendsense.category.entity.Category;
import com.spendsense.common.mapper.MappingConfig;
import org.mapstruct.*;

@Mapper(config = MappingConfig.class)
public interface CategoryMapper {

    Category toEntity(CreateCategoryRequest request);

    CategoryResponse toResponse(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(
            UpdateCategoryRequest request,
            @MappingTarget Category category
    );
}