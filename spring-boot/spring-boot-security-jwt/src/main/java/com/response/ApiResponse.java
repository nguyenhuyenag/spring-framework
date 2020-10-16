package com.response;

public class ApiResponse {

	private String code;
	private String desc;
	private Object data;

	public ApiResponse() {

	}

	public ApiResponse(String code, String desc, Object data) {
		this.code = code;
		this.desc = desc;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
