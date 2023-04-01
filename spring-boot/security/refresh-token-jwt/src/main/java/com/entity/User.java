package com.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

	// private static final long serialVersionUID = 5852418795372459352L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", //
			joinColumns = { @JoinColumn(name = "user_id") }, //
			inverseJoinColumns = { @JoinColumn(name = "role_id") } //
	)
	private Set<Role> roles;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
	public boolean isEnabled() {
		return false;
	}
	
	public Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		if (this.roles != null) {
			this.roles.forEach(role -> {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
			});
		}
		return authorities;
	}
	
	public String getStringAuthorities() {
		StringJoiner sj = new StringJoiner(",");
		if (this.roles != null) {
			this.roles.forEach(role -> {
				sj.add("ROLE_" + role.getName());
			});
		}
		return sj.toString();
	}

}
