package com.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.CriteriaService;

@Service
public class VocabServiceImpl implements CriteriaService {

	@Autowired
	private VocabRepository repository;

	@Autowired
	private EntityManager entityManager;

	Session session = entityManager.unwrap(Session.class);
	@SuppressWarnings("deprecation")
	Criteria criteria = session.createCriteria(Vocabulary.class);
	
	// Criteria
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Vocabulary> criteriaQuery = criteriaBuilder.createQuery(Vocabulary.class);
	Root<Vocabulary> root = criteriaQuery.from(Vocabulary.class);

	@Override
	public void CollectionParam() {
		List<String> words = Arrays.asList("bake", "concept", "theater");
		List<Vocabulary> list = repository.findVocabByWordList(words);
		list.forEach(System.out::println);
	}

	@Override
	public void findUserByEmails() {
		Set<String> emails = new HashSet<>(Arrays.asList("ac", "an", "ar"));
		// CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		// CriteriaQuery<Vocabulary> query = cb.createQuery(Vocabulary.class);
		Root<Vocabulary> user = criteriaQuery.from(Vocabulary.class);
		Path<String> emailPath = user.get("word");
		List<Predicate> predicates = new ArrayList<>();
		for (String email : emails) {
			predicates.add(criteriaBuilder.like(emailPath, email));
		}
		criteriaQuery.select(user) //
				.where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));

		List<Vocabulary> list = entityManager.createQuery(criteriaQuery).getResultList();
		list.forEach(t -> System.out.println(t));
	}

//	public void like() {
//		Session session = sessionFactory.openSession();
//		Criteria criteria = session.createCriteria(Vocabulary.class);
//		criteria.add(Restrictions.like("firstName", "John"));
//		List<Vocabulary> employees = criteria.list();
//		session.close();
//	}
	
	public void operatorByRestrictions() {
		// Add EQUAL criteria (eq, lt, le, gt, ge)
		criteria.add(Restrictions.eq("address", "usa"));
		
		// LIKE 
		criteria.add(Restrictions.like("name", "%th%"));
		
		// BETWEEN
		criteria.add(Restrictions.between("dateOfBirth", "startDate", "endDate"));
		
		// NULL, NOT NULL,...
		criteria.add(Restrictions.isNotNull("description"));
	}

	public void operatorsByExpression() {
		// Add EQUAL criteria
		Predicate equalPredicate = criteriaBuilder.equal(root.get("fieldName"), "valueToMatch");
		criteriaQuery.where(equalPredicate);

		// Add LIKE criteria
		String partialString = "";
		Predicate likePredicate = criteriaBuilder.like(root.get("fieldName"), "%" + partialString + "%");
		criteriaQuery.where(likePredicate);

		// Add sorting
		Order order = criteriaBuilder.asc(root.get("fieldName")); // Change to desc for descending order
		criteriaQuery.orderBy(order);

		// Execute the query
		List<Vocabulary> resultList = entityManager.createQuery(criteriaQuery).getResultList();
		System.out.println(resultList.size());
	}

}
