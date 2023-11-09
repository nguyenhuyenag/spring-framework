package com.entity.lifecycle;

import javax.persistence.Column;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

public class Auditing {

	@PrePersist // before save entity
	public void onPrePersist() {
	}

	@PostPersist // after save entity
	public void onPostPersist() {
	}

	@PreUpdate
	public void onPreUpdate() {
	}

	@PostUpdate // method chỉ được gọi khi dữ liệu thực sự bị thay đổi.
	public void onPostUpdate() {
	}

	@PostRemove
	public void onPostRemove() {
	}

	@PreRemove
	public void onPreRemove() {
	}

	@PostLoad
	public void onPostLoad() {
	}

	@PrePersist
	@PreUpdate
	@PreRemove
	private void beforeAnyOperation(Object object) {
	}

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	private long createdDate;

	@Column(name = "modified_date")
	@LastModifiedDate
	private long modifiedDate;

	// TODO
	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;

	// TODO
	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;

}
