package com.spendsense.budget.mapper;

import com.spendsense.budget.entity.Budget;
import com.spendsense.common.mapper.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface BudgetMapper {

    @Mapping(target = "allocatedAmount", source = "amount")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")

    // Calculated fields will be populated in the service
    @Mapping(target = "spentAmount", ignore = true)
    @Mapping(target = "remainingAmount", ignore = true)
    @Mapping(target = "usagePercentage", ignore = true)
    @Mapping(target = "status", ignore = true)

    BudgetResponse toResponse(Budget budget);

}