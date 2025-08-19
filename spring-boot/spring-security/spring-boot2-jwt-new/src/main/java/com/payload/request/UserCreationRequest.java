package com.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequest {

    /**
     * Nếu xảy ra lỗi -> MethodArgumentNotValidException.class (xem log)
     */
    // @Size(min = 3, message = "Username must be at least 3 characters")
    @Size(min = 3, message = "USERNAME_INVALID1") // <= ErrorCode.USERNAME_INVALID
    private String username;

    // @Size(min = 6, message = "Password must be at least 8 characters")
    @Size(min = 6, message = "PASSWORD_INVALID1")
    private String password;

    private String email;

}
