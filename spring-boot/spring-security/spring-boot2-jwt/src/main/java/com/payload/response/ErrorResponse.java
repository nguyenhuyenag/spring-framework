package com.payload.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "status", "error", "message", "path", "timestamp" })
public class ErrorResponse {

	// @JsonFormat(shape = JsonFormat.Shape.NUMBER) // 1641523030071
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date timestamp = new Date();
	
	private int status;
	private String error;
	private String message;
	private String path;

}
