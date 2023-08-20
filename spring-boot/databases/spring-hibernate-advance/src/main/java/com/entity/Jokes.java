package com.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jokes {

	private String type;
	
	// @JsonUnwrapped
	private Value value;
	
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
//	}

}
