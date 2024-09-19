package com.service;

import com.entity.SaoKe;
import com.mapper.SaoKeMapper;
import com.repository.SaoKeRepository;
import com.response.SaoKeResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class SaoKeService {

    @PersistenceContext
    private EntityManager entityManager;

    private final SaoKeMapper mapper;
    private final SaoKeRepository repository;

    public List<SaoKeResponse> listTransactions(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return mapper.toSaoKeResponse(repository.findAll());
        }
        return mapper.toSaoKeResponse(search(keyword));
    }

    private List<SaoKe> search(String keyword) {
        String sql = "SELECT * FROM sao_ke";

        StringJoiner joiner = new StringJoiner(" OR ");
        joiner.add("code LIKE '%" + keyword + "%'");
        joiner.add("notes LIKE '%" + keyword + "%'");
        sql += " WHERE " + joiner;

        Query query = entityManager.createNativeQuery(sql, SaoKe.class);
        return query.getResultList();
    }

    private List<SaoKe> advanceSearch(String keyword) {
        String[] keySearch = keyword.split(" ");

        String sql = "SELECT * FROM sao_ke";

        // WHERE [column LIKE '%Text%' OR column LIKE 'Hello%']
        StringJoiner joiner = new StringJoiner(" OR ");
        for (String key : keySearch) {
            joiner.add("code LIKE '%" + key + "%'");
            joiner.add("notes LIKE '%" + key + "%'");
        }

        joiner.add("notes LIKE '%" + keyword + "%'");

        if (joiner.length() > 0) {
            sql += " WHERE " + joiner;
        }

        Query query = entityManager.createNativeQuery(sql, SaoKe.class);
        List<SaoKe> result = query.getResultList();
        return result;
    }

}
