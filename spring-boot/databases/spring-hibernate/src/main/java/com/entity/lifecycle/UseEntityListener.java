package com.entity.lifecycle;

import javax.persistence.EntityListeners;
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

class AuditTrailListener {

	private static final Logger LOG = LoggerFactory.getLogger(AuditTrailListener.class);

	@PrePersist
	@PreUpdate
	@PreRemove
	private void beforeAnyUpdate(UseEntityListener user) {
		if (user.getId() == 0) {
			LOG.info("[USER AUDIT] About to add a user");
		} else {
			LOG.info("[USER AUDIT] About to update/delete user: " + user.getId());
		}
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	private void afterAnyUpdate(UseEntityListener user) {
		LOG.info("[USER AUDIT] add/update/delete complete for user: " + user.getId());
	}

	@PostLoad
	private void afterLoad(UseEntityListener user) {
		LOG.info("[USER AUDIT] user loaded from database: " + user.getId());
	}
}

@Getter
@EntityListeners(AuditTrailListener.class)
public class UseEntityListener {

	private Integer id;

}
