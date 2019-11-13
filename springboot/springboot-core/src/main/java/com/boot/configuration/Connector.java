package com.boot.configuration;

import lombok.Data;

@Data
public abstract class Connector {

	private String url;

	public abstract void connect();

}