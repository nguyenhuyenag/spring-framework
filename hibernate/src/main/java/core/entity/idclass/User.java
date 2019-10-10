package core.entity.idclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.RequiredArgsConstructor;

@Entity
@IdClass(UserId.class)
@Table(name = "student")
@RequiredArgsConstructor
public class User {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Id
	private Integer code;

	@Column
	private String name;

	public User(final Integer code, final String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}