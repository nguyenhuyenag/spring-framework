package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.UserRole;

@Repository
@Transactional
public class AppRoleDAO {

	@Autowired
	private EntityManager entityManager;

	public List<String> getRoleNames(Long userId) {
		String sql = "select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
				+ " where ur.appUser.userId = :userId ";
		System.out.println("SQL: " + sql);
		Query query = this.entityManager.createQuery(sql, String.class);
		query.setParameter("userId", userId);
		List<String> list = new ArrayList<>();
		Iterator<?> iterator = query.getResultList().iterator();
		while (iterator.hasNext()) {
			list.add(iterator.next().toString());
		}
		return list;
	}

}
