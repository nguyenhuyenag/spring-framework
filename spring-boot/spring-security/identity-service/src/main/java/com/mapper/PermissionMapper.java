package com.mapper;

import com.dto.request.PermissionRequest;
import com.dto.response.PermissionResponse;
import com.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
