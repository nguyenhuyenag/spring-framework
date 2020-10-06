package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

/*-
	ApplicationContext context = SpringApplication.run(LearnApplication.class, args);
	UserRepository userRepository = context.getBean(UserRepository.class);
	userRepository.findAll().forEach(System.out::println);
*/
// @Getter
// @Setter
// @AllArgsConstructor
// @RequiredArgsConstructor
@Entity
@Table(name = "t_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String firstname;
	private String lastname;

	@Column(name = "email_address")
	private String emailAddress;

	@UpdateTimestamp
	@Column(name = "execute_time")
	private Date executeTime;

	public User() {
		super();
	}

	public User(Integer id, String firstname, String lastname, String emailAddress, Date executeTime) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailAddress = emailAddress;
		this.executeTime = executeTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

}
