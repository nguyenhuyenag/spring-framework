package com.entity.manytoone.onetomany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*-
	Company company = new Company("LG");
	companyRepositoty.save(company);

	Staff staff1 = new Staff();
	staff1.setName(RandomStringUtils.randomAlphabetic(5));
	staff1.setCompany(company);
	staffRepository.save(staff1);

	Staff staff2 = new Staff();
	staff2.setName(RandomStringUtils.randomAlphabetic(5));
	staff2.setCompany(company);
	staffRepository.save(staff2);
	
	- Select * from clazz c, people p where c.id = p.clazz_id;

	- Quan hệ nhiều – một nhìn từ đối tượng Staff, đặt annotation @ManyToOne trong entity Staff
	
	- Nhiều Staff có cùng Company => @ManyToOne đặt ở Staff
*/
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = "company_id") // optional
	private Company company;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
