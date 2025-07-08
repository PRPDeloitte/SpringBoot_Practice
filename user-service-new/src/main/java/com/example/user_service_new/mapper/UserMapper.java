package com.example.user_service_new.mapper;

import com.example.user_service_new.dto.UserDTO;
import com.example.user_service_new.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
