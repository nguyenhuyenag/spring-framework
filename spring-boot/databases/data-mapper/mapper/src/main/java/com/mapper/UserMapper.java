package com.mapper;

import com.response.UserResponse;
import com.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    // @Mapping(target = "roles", ignore = true)
    // updateUser(@MappingTarget target, source)
    // void updateUser(@MappingTarget User user, UserUpdateRequest request);

}
