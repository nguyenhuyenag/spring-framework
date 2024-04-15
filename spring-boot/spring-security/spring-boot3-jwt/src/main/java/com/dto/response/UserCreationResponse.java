package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationResponse {

    private String id;
    private String username;
    // private String firstName;
    // private String lastName;
    private LocalDate birthday;
    // private Set<RoleResponse> roles;

}
