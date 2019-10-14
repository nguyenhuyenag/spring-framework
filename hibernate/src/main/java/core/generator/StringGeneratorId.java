package core.generator;

import java.io.Serializable;
import java.util.stream.Stream;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.spi.QueryImplementor;

public class StringGeneratorId implements IdentifierGenerator {

	private String prefix = "EMP";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		// Select all id
		QueryImplementor<String> query = session.createQuery("SELECT e.id FROM Employee e", String.class);
		try (Stream<String> stream = query.stream()) {
			Long max = stream.map(t -> t.replace(prefix, "")) // EMP0001 => 0001
					.mapToLong(Long::parseLong) // String to Long
					.max() 						// Tìm số lớn nhất
					.orElse(0L); 				// Nếu không có thì set là 0
			return prefix + String.format("%04d", max + 1); // EMP0002
		}
	}
}
