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
@Entity
@Table(name = "t_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(name = "e_mail")
	private String email;

	@UpdateTimestamp
	@Column(name = "execute_time")
	private Date executeTime;

	public User() {
		
	}

	public User(Integer id, String name, String email, Date executeTime) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.executeTime = executeTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

}
