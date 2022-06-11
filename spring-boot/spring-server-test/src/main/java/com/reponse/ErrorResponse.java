package com.reponse;

import java.util.Date;

import com.util.TimeUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private String timestamp;

	private int status;
	private String error;
	private String message;
	private String path;

	public ErrorResponse() {
		this.timestamp = TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

}
