package com.entity.onetoone.withSharedPrimaryKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class AddressSPK {

	@Id
	@Column(name = "user_id")
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private UserSPK user;

}
