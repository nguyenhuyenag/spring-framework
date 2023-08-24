package com.entity.lifecycle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

@Getter
@Entity
public class EntityLifecycle {

	private static final Logger LOG = LoggerFactory.getLogger(EntityLifecycle.class);

	@Id
	private Integer id;
	private String username;
	private String fullName;
	private String firstName;
	private String lastName;

	@PrePersist
	public void logNewUserAttempt() {
		LOG.info("Attempting to add new user with username: " + username);
	}

	@PostPersist
	public void logNewUserAdded() {
		LOG.info("Added user '" + username + "' with ID: " + id);
	}

	@PreRemove
	public void logUserRemovalAttempt() {
		LOG.info("Attempting to delete user: " + username);
	}

	@PostRemove
	public void logUserRemoval() {
		LOG.info("Deleted user: " + username);
	}

	@PreUpdate
	public void logUserUpdateAttempt() {
		LOG.info("Attempting to update user: " + username);
	}

	@PostUpdate
	public void logUserUpdate() {
		LOG.info("Updated user: " + username);
	}

	@PostLoad
	public void logUserLoad() {
		fullName = firstName + " " + lastName;
	}

}
