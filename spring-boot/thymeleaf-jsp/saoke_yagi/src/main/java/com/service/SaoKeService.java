package com.service;

import com.entity.SaoKe;
import com.repository.SaoKeRepository;
import lombok.RequiredArgsConstructor;
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

    public List<SaoKe> search(String keyword) {
        String[] keySearch = keyword.split(" ");

        String sql = "SELECT * FROM sao_ke";

        // WHERE [column LIKE '%Text%' OR column LIKE 'Hello%' OR column LIKE 'That%']
        StringJoiner joiner = new StringJoiner(" OR ");
        for (String key : keySearch) {
            joiner.add("code LIKE '%" + key + "%'");
            joiner.add("notes LIKE '%" + key + "%'");
        }

        if (joiner.length() > 0) {
            sql += " WHERE " + joiner;
        }

        // Create a native query with the SQL string and specify the entity class
        Query query = entityManager.createNativeQuery(sql, SaoKe.class);
        List<SaoKe> list = query.getResultList();
        return list;
    }


}
