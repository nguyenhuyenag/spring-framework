package com.entity.enumerated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

/*-

	Programing emp = new Programing();
	emp.setName("Java");
	emp.setPosition(PositionEnum.DEVELOPER);
	repositoty.save(emp);

	Optional<Programing> p = repositoty.findById(1);
	p.ifPresent(t -> System.out.println(t));

*/
@Getter
@Setter
@Entity
@Table(name = "programing")
public class Programing {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "position")
	@Enumerated(EnumType.STRING) // <- here
	private PositionEnum position;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
