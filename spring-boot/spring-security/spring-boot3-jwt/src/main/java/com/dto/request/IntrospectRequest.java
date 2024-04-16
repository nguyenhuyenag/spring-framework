package com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @FieldDefaults(level = AccessLevel.PRIVATE)
public class IntrospectRequest {

    private String token;

}
