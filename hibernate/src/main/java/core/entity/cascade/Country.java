package core.entity.cascade;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*-
	// insert
	Country country = new Country();
	country.setName("Viet Nam");

	Province pro1 = new Province();
	pro1.setName("An Giang");
	pro1.setCountry(country);

	Province pro2 = new Province();
	pro2.setName("Dong Thap");
	pro2.setCountry(country);

	country.getListProvince().add(pro1);
	country.getListProvince().add(pro2);

	countryRepositoty.save(country);

	// delete
	Optional<Country> entity = countryRepositoty.findById(1);
	entity.ifPresent(t -> countryRepositoty.delete(t));
*/
@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToMany( //
		mappedBy = "country", //
		fetch = FetchType.LAZY, //
		cascade = { CascadeType.REMOVE, CascadeType.PERSIST } //
	)
	private Set<Province> listProvince = new HashSet<>();

	@Override
	public String toString() {
		return "Country [id=" + this.id + ", name=" + this.name + "]";
	}

}
