package dev.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*-
	ApplicationContext context = SpringApplication.run(LearnApplication.class, args); // context
	UserRepository userRepository = context.getBean(UserRepository.class); // bean
	userRepository.findAll().forEach(System.out::println); // su dung
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;

	@UpdateTimestamp // hibernate
	@Column(name = "execute_time")
	private Date executeTime;

	@Override
	public String toString() {
		return "{id: " + this.id + ", name: " + this.name + ", email: " + this.email + "}";
	}

}
