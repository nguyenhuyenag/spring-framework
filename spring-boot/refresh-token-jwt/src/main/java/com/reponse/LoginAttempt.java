package com.reponse;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginAttempt {

	private int count;
	private Date lockTime;

	public LoginAttempt(int count, Date lockTime) {
		this.count = count;
		this.lockTime = lockTime;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
