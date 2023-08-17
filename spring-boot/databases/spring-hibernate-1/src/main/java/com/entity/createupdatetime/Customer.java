package com.entity.createupdatetime;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/*-
 * Spring @CreatedDate và LastModifiedDate cần thêm cấu hình:
 * 
 * 		Add '@EnableJpaAuditing' -> Application.class
 * 
 * 		Add '@EntityListeners(AuditingEntityListener.class)'
 */
@Data
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@org.hibernate.annotations.CreationTimestamp
	@Column(updatable = false)
	private Date createdAt;

	@org.hibernate.annotations.UpdateTimestamp
	private LocalDateTime updatedAt;

	// @org.springframework.data.annotation.CreatedDate
	// @Temporal(TemporalType.TIMESTAMP) // Unnecessary ?
	// @Column(updatable = false)
	// private Date createdAt;

	// @org.springframework.data.annotation.LastModifiedDate
	// private LocalDateTime updatedAt;

	public Customer(final String name, final String email) {
		this.name = name;
		this.email = email;
	}

}