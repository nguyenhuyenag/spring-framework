package com.service;

import com.dto.request.RoleRequest;
import com.dto.response.PermissionResponse;
import com.dto.response.RoleResponse;
import com.entity.Role;
// import com.mapper.RoleMapper;
import com.repository.PermissionRepository;
import com.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    // RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        var entity = new Role();
        BeanUtils.copyProperties(request, entity, "permissions");

        // Tạo permission
        var permissions = permissionRepository.findAllById(request.getPermissions());
        entity.setPermissions(new HashSet<>(permissions));

        entity = roleRepository.save(entity);

        var response = new RoleResponse();
        // return roleMapper.toRoleResponse(entity);
        BeanUtils.copyProperties(entity, response, "permissions");
        var roles = entity.getPermissions().stream().map(p -> {
            PermissionResponse permissionResponse = new PermissionResponse();
            BeanUtils.copyProperties(p, permissionResponse);
            return permissionResponse;
        }).toList();
        response.setPermissions(new HashSet<>(roles));
        return response;
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(r -> {
                    var response = new RoleResponse();
                    // return roleMapper.toRoleResponse(entity);
                    BeanUtils.copyProperties(r, response);
                    return response;
                })
                .toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
