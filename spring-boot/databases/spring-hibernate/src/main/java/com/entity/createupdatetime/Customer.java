package com.entity.createupdatetime;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/*-
 * - Spring @CreatedDate và @LastModifiedDate cần thêm cấu hình:
 * 
 * 		Add '@EnableJpaAuditing' -> Application.class
 * 		Add '@EntityListeners(AuditingEntityListener.class)'
 * 
 * - Định dạng thời gian cho field
 * 
 * 			@Temporal(TemporalType.TIME)			//	10:03:33
			@Temporal(TemporalType.DATE) 			// 	2023-08-24
			@Temporal(TemporalType.TIMESTAMP)		// 	2023-08-24 10:03:33.0
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
// @EntityListeners(AuditingEntityListener.class)
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String email;

	@org.hibernate.annotations.CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@org.hibernate.annotations.UpdateTimestamp
	private Date updatedAt;

	// @org.springframework.data.annotation.CreatedDate
	// @Temporal(TemporalType.TIMESTAMP) // Unnecessary ?
	// @Column(updatable = false)
	// private Date createdAt;

	// @org.springframework.data.annotation.LastModifiedDate
	// private LocalDateTime updatedAt;
	
	private int nam;
	
    @PrePersist
    public void prePersist() {
        this.nam = createdAt != null ? createdAt.getYear() : Year.now().getValue();
    }

	public Customer(final String name, final String email) {
		this.name = name;
		this.email = email;
	}

}