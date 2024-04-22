package com.service;

import com.dto.request.PermissionRequest;
import com.dto.response.PermissionResponse;
import com.entity.Permission;
import com.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true)
public class PermissionService {

    private PermissionRepository permissionRepository;
    // PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission entity = new Permission();
        BeanUtils.copyProperties(request, entity);
        // Permission entity = permissionMapper.toPermission(request);
        entity = permissionRepository.save(entity);
        PermissionResponse response = new PermissionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public List<PermissionResponse> getAll() {
        List<PermissionResponse> result = new ArrayList<>();
        permissionRepository.findAll().forEach(p -> {
            PermissionResponse response = new PermissionResponse();
            BeanUtils.copyProperties(p, response);
            result.add(response);
        });
        return result;
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }

}
