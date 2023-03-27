package com.reponse;

import java.util.Date;

import com.util.TimeUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	// @JsonFormat(shape = JsonFormat.Shape.NUMBER) // 1641523030071
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String timestamp;

	private int status;
	private String error;
	private String message;
	private String path;

	public ErrorResponse() {
		this.timestamp = TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

}
