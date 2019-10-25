package jwt.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import jwt.enums.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
// public class Role implements Serializable {
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	@Enumerated(EnumType.STRING)
	private RoleTypes name;

	@JoinTable( //
		name = "user_role", //
		joinColumns = { @JoinColumn(name = "role_id") }, //
		inverseJoinColumns = { @JoinColumn(name = "user_id") } //
	)
	@ManyToMany()
	private List<User> listUsers = new ArrayList<>();

	@Override
	public String getAuthority() {
		return this.name.name();
	}

}