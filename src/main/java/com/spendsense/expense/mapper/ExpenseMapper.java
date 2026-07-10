package com.spendsense.expense.mapper;

import com.spendsense.common.mapper.MapperConfig;
import com.spendsense.expense.dto.request.CreateExpenseRequest;
import com.spendsense.expense.dto.request.UpdateExpenseRequest;
import com.spendsense.expense.dto.response.ExpenseResponse;
import com.spendsense.expense.entity.Expense;
import org.mapstruct.*;

@Mapper(config = MapperConfig.class)
public interface ExpenseMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "user", ignore = true)
    Expense toEntity(CreateExpenseRequest request);

    @Mapping(target = "category", source = "category.name")
    ExpenseResponse toResponse(Expense expense);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "user", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateExpenseRequest request, @MappingTarget Expense expense);
}