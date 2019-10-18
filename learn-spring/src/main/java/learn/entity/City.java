package learn.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String district;

	private String name;

	private Integer population;

	@ManyToOne
	@JoinColumn(name = "CountryCode")
	private Country country;

}
