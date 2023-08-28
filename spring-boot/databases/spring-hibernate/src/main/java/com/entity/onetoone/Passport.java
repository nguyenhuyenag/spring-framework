package com.entity.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Mối quan hệ này chỉ ra rằng một thực thể A tương ứng với một thực thể B và
 * ngược lại
 *
 */
@Data
@Entity
@NoArgsConstructor
public class Passport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String passportNumber;

	@OneToOne
	@JoinColumn(name = "person_id")
	private Person person;

}
