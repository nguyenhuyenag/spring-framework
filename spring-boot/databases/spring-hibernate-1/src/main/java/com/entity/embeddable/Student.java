package com.entity.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "student")
public class Student {

	// Phải implement Serializable
	@Embeddable
	@AllArgsConstructor
	@RequiredArgsConstructor
	public class StudentId implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;

		@Column
		private Integer code;

		public StudentId(final int code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
		}

	}

	@EmbeddedId
	private StudentId id;

	@Column
	private String name;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
