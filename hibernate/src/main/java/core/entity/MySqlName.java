package core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/*-
 *	MySQL				JPA
 *
 *	name				name
 *	Name				name
 *	NAME				name
 *
 *	my_name				myName
 *
 *	camelCase			@Column(name = "camelcase")
 *						String camelCase;
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "mysql_name")
public class MySqlName implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String age;
	private String phone;

	@Column(name = "camelcase")
	private String camelCase;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "language_name")
	private String language;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
}
