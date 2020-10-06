package com.boot.configuration;

// @Data
public abstract class Connector {

	private String url;

	public abstract void connect();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
