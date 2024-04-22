package com.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String password;

    // @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate birthday;
    List<String> roles;
}
