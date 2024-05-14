package com.service;

import com.response.UserResponse;
import com.entity.User;
import com.mapper.UserMapper;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserResponse getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // return mapByMapStruct(user);

        return mapByModelMapper(user);
    }

    public UserResponse mapByMapStruct(User user) {
        return userMapper.toUserResponse(user);
    }

    public UserResponse mapByModelMapper(User user) {
        var modelMapper = new ModelMapper();

        TypeMap<User, UserResponse> propertyMapper = modelMapper.createTypeMap(User.class, UserResponse.class);

        // Skip properties
        propertyMapper.addMappings(mapper -> {
            // mapper.skip(UserResponse::setUsername);
            mapper.skip(UserResponse::setBirthday);
        });

        // Map and transform birthday2 (field không có trong User)
        propertyMapper.addMappings(mapper ->
                mapper.map(src -> {
                    LocalDate birthday = src.getBirthday();
                    if (birthday != null) {
                        return Date.from(birthday.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    }
                    return null;
                }, (dest, v) -> dest.setBirthday2((Date) v))
        );

        // Auto-Skip Null Properties
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        // Circular Referenced Objects -> Caused by: java.lang.StackOverflowError
        modelMapper.getConfiguration().setPreferNestedProperties(false);

        return modelMapper.map(user, UserResponse.class);
    }

}
