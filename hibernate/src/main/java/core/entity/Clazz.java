package core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table
public class Clazz implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	public Clazz(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@PrePersist
	public void prePersist() {
		System.out.println("pre persist!");
	}

	@PostPersist
	public void postPersist() {
		System.out.println("post persist!");
	}

	@PreUpdate
	public void preUpdate() {
		System.out.println("pre update!");
	}

	@PostUpdate
	public void postUpdate() {
		System.out.println("post update!");
	}

	@PreRemove
	public void preRemove() {
		System.out.println("pre remove!");
	}

	@PostRemove
	public void postRemove() {
		System.out.println("post remove!");
	}

}
