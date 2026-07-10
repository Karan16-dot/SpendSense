package com.spendsense.income.mapper;

import com.spendsense.common.mapper.MapperConfig;
import com.spendsense.income.dto.request.CreateIncomeRequest;
import com.spendsense.income.dto.request.UpdateIncomeRequest;
import com.spendsense.income.dto.response.IncomeResponse;
import com.spendsense.income.entity.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface IncomeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Income toEntity(CreateIncomeRequest request);

    IncomeResponse toResponse(Income income);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateEntity(UpdateIncomeRequest request,
                      @MappingTarget Income income);
}