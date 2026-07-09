package com.spendsense.category.dto;

import com.spendsense.category.entity.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "Category name is required")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Icon is required")
    @Size(max = 100, message = "Icon cannot exceed 100 characters")
    private String icon;

    @NotBlank(message = "Color is required")
    @Pattern(
            regexp = "^#([A-Fa-f0-9]{6})$",
            message = "Color must be a valid HEX value"
    )
    private String color;

    @NotNull(message = "Category type is required")
    private CategoryType type;
}