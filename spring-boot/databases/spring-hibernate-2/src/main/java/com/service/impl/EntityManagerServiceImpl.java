package com.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Vocabulary;
import com.service.EntityManagerService;

@Service
public class EntityManagerServiceImpl implements EntityManagerService {

	@PersistenceContext // or even @Autowired
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public void findAllByQuery() {
		String sql = "select * from vocab";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		System.out.println(list.size());
	}

	@Override
	@Transactional // Spring transaction
	public void insert() {
		Vocabulary v = new Vocabulary("carry", "'ke-ri", "mang theo");
		entityManager.persist(v);
		System.out.println(v.toString());
	}

	@Override
	public void findOne() {
		String sql = "select v from Vocabulary v where v.word = :param_word";
		TypedQuery<Vocabulary> typedQuery = entityManager.createQuery(sql, Vocabulary.class);
		typedQuery.setParameter("param_word", "alive");
		Vocabulary v = typedQuery.getSingleResult();
		// List<Vocabulary> list = typedQuery.getResultList();
		if (v != null) {
			System.out.println(v);
		}
	}

	@Override
	public void findAllByTypedQuery() {
		String sql = "select v from Vocabulary v";
		TypedQuery<Vocabulary> typedQuery = entityManager.createQuery(sql, Vocabulary.class);
		List<Vocabulary> list = typedQuery.getResultList();
		list.forEach(t -> System.out.println(t));
	}

//	@Override
//	public void sortByCriteriaQuery() {
//		
//	}

}
