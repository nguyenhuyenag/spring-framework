package com.controller;

import com.dto.request.*;
import com.dto.response.AuthenticationResponse;
import com.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import com.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("login")
    public ApiResponse<?> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("verify-token")
    public ApiResponse<?> authenticate(@RequestBody IntrospectRequest request) throws ParseException {
        IntrospectResponse response = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping("/refresh-token")
    public ApiResponse<?> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

}
