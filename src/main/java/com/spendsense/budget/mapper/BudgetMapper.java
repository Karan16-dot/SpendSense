package com.spendsense.budget.mapper;

import com.spendsense.budget.dto.request.CreateBudgetRequest;
import com.spendsense.budget.dto.request.UpdateBudgetRequest;
import com.spendsense.budget.dto.response.BudgetResponse;
import com.spendsense.budget.entity.Budget;
import com.spendsense.budget.entity.BudgetStatus;
import com.spendsense.common.mapper.MapperConfig;
import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BudgetMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Budget toEntity(CreateBudgetRequest request);

    @Mapping(target = "allocatedAmount", source = "amount")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "spentAmount", ignore = true)
    @Mapping(target = "remainingAmount", ignore = true)
    @Mapping(target = "usagePercentage", ignore = true)
    @Mapping(target = "status", ignore = true)
    BudgetResponse toResponse(Budget budget);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(UpdateBudgetRequest request,
                      @MappingTarget Budget budget);


    default BudgetResponse enrich(
            BudgetResponse response,
            BigDecimal spentAmount,
            BigDecimal remainingAmount,
            Double usagePercentage,
            BudgetStatus status) {

        response.setSpentAmount(spentAmount);
        response.setRemainingAmount(remainingAmount);
        response.setUsagePercentage(usagePercentage);
        response.setStatus(status);

        return response;
    }

}