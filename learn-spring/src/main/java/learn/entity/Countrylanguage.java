package learn.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Countrylanguage implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CountrylanguagePK id;

	private String isOfficial;

	private Float percentage;

	@ManyToOne
	@JoinColumn(name = "CountryCode")
	private Country country;

}