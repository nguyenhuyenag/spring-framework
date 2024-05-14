package com.mapper;

import com.response.UserResponse;
import com.entity.User;
import org.mapstruct.Mapper;

/*-
 * How to run? 	-> mvn clean install
 * 				-> View /target/generated-sources/annotations/
 */
// Đánh dấu một mapping interface và cho phép MapStrust hoạt động trên interface này
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    // @Mapping(target = "roles", ignore = true)
    // updateUser(@MappingTarget target, source)
    // void updateUser(@MappingTarget User user, UserUpdateRequest request);

    // @Mapping(source = "statusCategory", target = "status") // Map 2 field khác nhau
    // @Mapping(source = "createDate", target = "createDate", dateFormat = "dd/MM/yyyy")
    // @Mapping(source = "updateTime", target = "updateTime", dateFormat = "dd-MM-yyyy HH:mm:ss")
    // CategoryResDto categoryToCategoryDto(Category category);

}
