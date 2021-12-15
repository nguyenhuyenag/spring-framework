package com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -5898217703489466982L;

	@Id
	private String username;
	private String password;
	private int disabled = 0;
	private int lockAttempt = 0;
	private String lockAttemptTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLockAttemptTime() {
		return lockAttemptTime;
	}

	public void setLockAttemptTime(String lockAttemptTime) {
		this.lockAttemptTime = lockAttemptTime;
	}

	public int getDisabled() {
		return disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}

	public int getLockAttempt() {
		return lockAttempt;
	}

	public void setLockAttempt(int lockAttempt) {
		this.lockAttempt = lockAttempt;
	}

}
