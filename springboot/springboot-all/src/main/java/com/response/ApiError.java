package com.response;

import com.util.DateTimeUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

	private final String timestamp = DateTimeUtils.getNowByPattern("dd-MM-yyyy hh:mm:ss");
	private int status;
	private String error;
	private String message;
	private String path;

}
