package com.service;

import com.dto.request.RoleRequest;
import com.dto.response.PermissionResponse;
import com.dto.response.RoleResponse;
import com.entity.Permission;
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
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        if (CollectionUtils.isEmpty(permissions)) {
            log.info("Role not found");
        }
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

//    public List<RoleResponse> getAll() {
//        return roleRepository.findAll()
//                .stream()
//                .map(entity -> {
//                    // return roleMapper.toRoleResponse(entity);
//                    var response = new RoleResponse();
//                    BeanUtils.copyProperties(entity, response);
//
//                    Set<PermissionResponse> setPr = new HashSet<>();
//                    entity.getPermissions().forEach(p -> {
//                        var pr = new PermissionResponse();
//                        BeanUtils.copyProperties(p, pr);
//                        setPr.add(pr);
//                    });
//
//                    response.setPermissions(setPr);
//                    return response;
//                }).toList();
//    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(entity -> {
                    // return roleMapper.toRoleResponse(entity);
                    var response = new RoleResponse();
                    BeanUtils.copyProperties(entity, response);

                    Set<PermissionResponse> permissionResponses = new HashSet<>();
                    entity.getPermissions().forEach(permission -> {
                        var permissionResponse = new PermissionResponse();
                        BeanUtils.copyProperties(permission, permissionResponse);
                        permissionResponses.add(permissionResponse);
                    });

                    response.setPermissions(permissionResponses);
                    return response;
                }).toList();
    }


    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
