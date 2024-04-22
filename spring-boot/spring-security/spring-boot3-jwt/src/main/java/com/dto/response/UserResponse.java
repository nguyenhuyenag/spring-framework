package com.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    private String id;
    private String username;
    // private String firstName;
    // private String lastName;
    private LocalDate birthday;
    private Set<RoleResponse> roles;

}
