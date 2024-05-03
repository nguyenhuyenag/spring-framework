package com.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    private String username;
    private String password;

}
