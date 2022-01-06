package com.reponse;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	/*-
	 * {
	    "timestamp": "2022-01-04T02:47:53.158+00:00",
	    "status": 403,
	    "error": "Forbidden",
	    "message": "",
	    "path": "/api/get-json"
	}
	 */
	// customizing timestamp serialization format
	@JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

	public ErrorResponse() {
		this.timestamp = new Date();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
