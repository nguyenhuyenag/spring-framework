package learn.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
// @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String district;

	private String name;

	private Integer population;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "CountryCode")
	private Country country;

}
