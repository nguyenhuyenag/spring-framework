package com.service;

import com.dto.PageResponse;
import com.dto.SearchRequest;
import com.entity.CoQuanBhxh;
import com.repository.CoQuanBhxhRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoQuanBhxhService {

    private final CoQuanBhxhRepository repository;

    /**
     * Tìm kiếm tổng hợp: ưu tiên mã chính xác trước, nếu không có thì tìm full-text.
     */
    public PageResponse<CoQuanBhxh> search(SearchRequest req) {
        Pageable pageable = PageRequest.of(
                req.getPage(),
                req.getSize(),
                Sort.by(Sort.Direction.ASC, "id")
        );

        Page<CoQuanBhxh> page;

        // 1. Nếu có mã cơ quan → tìm chính xác
        if (req.getMaCoQuan() != null && !req.getMaCoQuan().isBlank()) {
            page = repository.findByMaCoQuan(req.getMaCoQuan(), pageable);
        }
        // 2. Nếu có mã tỉnh → tìm theo tỉnh
        else if (req.getMaTinh() != null && !req.getMaTinh().isBlank()) {
            page = repository.findByMaTinh(req.getMaTinh(), pageable);
        }
        // 3. Nếu có mã phường/xã → tìm theo phường/xã
        else if (req.getMaPhuongXa() != null && !req.getMaPhuongXa().isBlank()) {
            page = repository.findByMaPhuongXa(req.getMaPhuongXa(), pageable);
        }
        // 4. Nếu có kkqm → tìm theo trạng thái
        else if (req.getKkqm() != null) {
            page = repository.findByKkqm(req.getKkqm(), pageable);
        }
        // 5. Nếu có keyword → full-text search
        else if (req.getKeyword() != null && !req.getKeyword().isBlank()) {
            page = repository.searchByKeyword(req.getKeyword(), pageable);
        }
        // 6. Không có điều kiện → lấy tất cả
        else {
            page = repository.findAll(pageable);
        }

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    /**
     * Tìm theo ID.
     */
    public CoQuanBhxh findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
