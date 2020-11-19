package com.boot.jpa.criteria;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public class UserRepositoryByCriteria {

	@PersistenceContext
	private EntityManager em;

	public User getUserByEmail(String email) {
		User user = null;
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			Predicate condition = builder.equal(root.get("email"), email); // like, and, notEqual, ...
			query.select(root).where(condition);
			user = em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			// e.printStackTrace();
		}
		return user;
	}

}
