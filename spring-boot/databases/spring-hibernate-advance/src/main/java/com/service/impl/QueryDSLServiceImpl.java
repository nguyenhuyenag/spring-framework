package com.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Person;
import com.entity.QPerson;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.service.QueryDSLService;

@Service
public class QueryDSLServiceImpl implements QueryDSLService {

	@Autowired
	EntityManager entityManager;

	@Override
	public void findOne() {
		QPerson person = QPerson.person;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		Person p = queryFactory.selectFrom(person).where(person.firstname.eq("Nancy")).fetchOne();
		System.out.println(p);
	}

	@Override
	public void findAllAndSort() {
		QPerson person = QPerson.person;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		List<Person> list = queryFactory.selectFrom(person).orderBy(person.firstname.desc()).fetch();
		list.forEach(t -> System.out.println(t));
	}

	@Override
	public void forAndCondition() {
		QPerson person = QPerson.person;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		List<Person> list = queryFactory.selectFrom(person)
				.where(person.firstname.eq("Adam"), person.firstname.eq("Andrew")).fetch();
		list.forEach(t -> System.out.println(t));
	}

	@Transactional
	@Override
	public void update() {
		QPerson person = QPerson.person;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		queryFactory.update(person).where(person.firstname.eq("Adam")).set(person.surname, "Jonh").execute();
	}

	@Transactional
	@Override
	public void delete() {
		QPerson person = QPerson.person;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		queryFactory.delete(person)
			.where(person.firstname.eq("Adam"))
			.execute();
	}

}
