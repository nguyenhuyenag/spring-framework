package learn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CountrylanguagePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String language;

	@Column(insertable = false, updatable = false)
	private String countryCode;

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CountrylanguagePK)) {
			return false;
		}
		CountrylanguagePK castOther = (CountrylanguagePK) other;
		return this.countryCode.equals(castOther.countryCode) && this.language.equals(castOther.language);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.countryCode.hashCode();
		hash = hash * prime + this.language.hashCode();
		return hash;
	}
}