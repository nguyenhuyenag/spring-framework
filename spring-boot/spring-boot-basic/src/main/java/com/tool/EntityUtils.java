package com.tool;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.entity.User;

public class EntityUtils {

	/**
	 * getTableName @Table(name = "t_user") -> 't_user'
	 */
	public static String getTableName(Class<?> entityClass) {
		Table annotation = entityClass.getAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		} else {
			// Handle the case when the @Table annotation is not present
			// or return the entity class name as the default table name.
			return entityClass.getSimpleName();
		}
	}

	/**
	 * Returns the table name for a given entity type in the {@link EntityManager}.
	 */
	public static <T> String getTableName(EntityManager em, Class<T> entityClass) {
		/*
		 * Check if the specified class is present in the metamodel. Throws
		 * IllegalArgumentException if not.
		 */
		Metamodel meta = em.getMetamodel();
		EntityType<T> entityType = meta.entity(entityClass);
		// Check whether @Table annotation is present on the class.
		Table t = entityClass.getAnnotation(Table.class);
		return t == null ? entityType.getName() : t.name();
	}

	public static void main(String[] args) {
		System.out.println("TableName: " + getTableName(User.class));
	}

}
