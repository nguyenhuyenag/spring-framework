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

    private String username;

    /**
     * Nếu xảy ra lỗi -> MethodArgumentNotValidException.class (xem log)
     */
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    private String email;

}
