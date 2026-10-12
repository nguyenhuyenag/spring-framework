package com.repository;

import com.entity.CoQuanBhxh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoQuanBhxhRepository extends ElasticsearchRepository<CoQuanBhxh, Integer> {

    // Tìm chính xác theo mã cơ quan
    List<CoQuanBhxh> findByMaCoQuan(String maCoQuan);

    Page<CoQuanBhxh> findByMaCoQuan(String maCoQuan, Pageable pageable);

    // Tìm theo mã tỉnh
    Page<CoQuanBhxh> findByMaTinh(String maTinh, Pageable pageable);

    // Tìm theo mã phường/xã
    Page<CoQuanBhxh> findByMaPhuongXa(String maPhuongXa, Pageable pageable);

    // Tìm theo kkqm
    Page<CoQuanBhxh> findByKkqm(Integer kkqm, Pageable pageable);

    // Tìm theo tên cơ quan (full-text)
    Page<CoQuanBhxh> findByTenCoQuanContaining(String keyword, Pageable pageable);

    // Query nâng cao: tìm theo nhiều field cùng lúc (multi-match)
    @Query("""
                {
                  "bool": {
                    "should": [
                      { "match": { "tencoquan": "?0" } },
                      { "match": { "tenphuongxa": "?0" } },
                      { "match": { "tentinhtp": "?0" } }
                    ]
                  }
                }
            """)
    Page<CoQuanBhxh> searchByKeyword(String keyword, Pageable pageable);

}
