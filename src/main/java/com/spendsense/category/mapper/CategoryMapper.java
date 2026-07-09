package com.spendsense.category.mapper;

import com.spendsense.category.dto.CategoryRequest;
import com.spendsense.category.dto.CategoryResponse;
import com.spendsense.category.dto.CategoryUpdateRequest;
import com.spendsense.category.entity.Category;
import com.spendsense.common.mapper.MappingConfig;
import org.mapstruct.*;

@Mapper(config = MappingConfig.class)
public interface CategoryMapper {

    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(
            CategoryUpdateRequest request,
            @MappingTarget Category category
    );
}