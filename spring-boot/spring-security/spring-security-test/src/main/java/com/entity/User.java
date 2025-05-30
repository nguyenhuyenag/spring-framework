//package com.entity;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "user")
//public class User {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "user_id")
//	private Integer userId;
//
//	private String password;
//	private String username;
//	private int enabled; // 0 or 1
//	
//	public boolean isDisabled() {
//		return this.enabled == 0;
//	}
//
//	// Important `fetch = FetchType.EAGER`
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "user_roles", //
//		joinColumns = { @JoinColumn(name = "user_id") }, //
//		inverseJoinColumns = { @JoinColumn(name = "role_id") } //
//	)
//	private Set<Role> userRoles = new HashSet<>();
//
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
//	}
//
//}
