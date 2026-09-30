package com.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
	
	private long timestamp;
	private int code;
	private String path;	
	private String message;
	
	public Response withTimestamp(final long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	
	public Response withCode(final int code) {
		this.code = code;
		return this;
	}
	
	public Response withMessage(final String message) {
		this.message = message;
		return this;
	}
	
	public Response withPath(final String path) {
		this.path = path;
		return this;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
	public static void main(String[] args) {
		Response r = new Response().withCode(200).withTimestamp(1212).withMessage("asasas");
		System.out.println(r);
	}
	
}
