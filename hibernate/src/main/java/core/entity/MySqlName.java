package core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

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
@Entity
@Table(name = "mysql_name")
public class MySqlName implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	String name;
	String age;
	String phone;

	@Column(name = "camelcase")
	String camelCase;

	@Column(name = "card_number")
	String cardNumber;

	@Column(name = "language_name")
	String language;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
