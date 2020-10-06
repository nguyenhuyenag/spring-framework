package com.boot.component.servicerepository;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Firefox {

	@SuppressWarnings("unused")
	private String name;

	public Firefox() {
		super();
	}

	public Firefox(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
