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
	Companys company = new Companys();
	company.setName("Google");

	Employees emp1 = new Employees();
	emp1.setName("Gmail");
	emp1.setCompanys(company);

	Employees emp2 = new Employees();
	emp2.setName("Google photo");
	emp2.setCompanys(company);

	company.getListEmployees().add(emp1);
	company.getListEmployees().add(emp2);

	companysRepositoty.save(company);

	// delete
	Optional<Companys> entity = companysRepositoty.findById(2);
	entity.ifPresent(t -> companysRepositoty.delete(t));
*/
@Getter
@Setter
@Entity
@Table(name = "companys")
public class Companys {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	// @Cascade(value= {CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companys", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	private Set<Employees> listEmployees = new HashSet<>();

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

}