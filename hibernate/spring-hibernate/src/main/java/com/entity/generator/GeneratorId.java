package com.entity.generator;

import java.io.Serializable;
import java.util.stream.Stream;

import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.spi.QueryImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

public class GeneratorId implements IdentifierGenerator {

	private final String PREFIX = "EMP";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		QueryImplementor<String> query = session.createQuery("SELECT e.id FROM Employee e", String.class);
		try (Stream<String> stream = query.stream()) {
			Long max = stream.map(t -> t.replace(PREFIX, "")) // EMP0001 => 0001
							 .mapToLong(Long::parseLong) // String to Long
							 .max()						 // Tìm số lớn nhất
							 .orElse(0L); 				 // Nếu không có thì set là 0
			return PREFIX + String.format("%04d", max + 1); // EMP0002
		}
	}
}
