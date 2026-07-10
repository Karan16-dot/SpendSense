package com.spendsense.user.mapper;

import com.spendsense.common.mapper.MapperConfig;
import com.spendsense.user.dto.RegisterRequest;
import com.spendsense.user.dto.RegisterResponse;
import com.spendsense.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "categories", ignore = true)
    User toEntity(RegisterRequest request);

    @Mapping(target = "message", constant = "Registration Successful")
    RegisterResponse toResponse(User user);
}
