package core.entity.manytoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import core.entity.Clazz;
import lombok.Data;

/**
		// clazz
		Clazz clazz = new Clazz(RandomStringUtils.randomAlphabetic(5));
		clazzRepository.save(clazz);

		// people
		People people1 = new People();
		people1.setName(RandomStringUtils.randomAlphabetic(5));
		people1.setClazz(clazz);
		peopleRepository.save(people1);

		// people
		People people2 = new People();
		people2.setName(RandomStringUtils.randomAlphabetic(5));
		people2.setClazz(clazz);
		peopleRepository.save(people2);

		// people
		People people3 = new People();
		people3.setName(RandomStringUtils.randomAlphabetic(5));
		people3.setClazz(clazz);
		peopleRepository.save(people3);
		
		select *
		from clazz c, people p
		where c.id = p.clazz_id;
 */
@Data
@Entity
@Table(name = "people")
public class People {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = "clazz_id")
	private Clazz clazz;

}
