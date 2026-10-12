package com.controller;

import com.dto.PageResponse;
import com.dto.SearchRequest;
import com.entity.CoQuanBhxh;
import com.service.CoQuanBhxhService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/co-quan-bhxh")
@RequiredArgsConstructor
public class CoQuanBhxhController {

    private final CoQuanBhxhService service;

    /**
     * GET /api/co-quan-bhxh/search?keyword=bảo hiểm&maTinh=01&page=0&size=20
     */
    @GetMapping("/search")
    public ResponseEntity<PageResponse<CoQuanBhxh>> search(@ModelAttribute SearchRequest request) {
        return ResponseEntity.ok(service.search(request));
    }

    /**
     * GET /api/co-quan-bhxh/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CoQuanBhxh> findById(@PathVariable Integer id) {
        CoQuanBhxh result = service.findById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
