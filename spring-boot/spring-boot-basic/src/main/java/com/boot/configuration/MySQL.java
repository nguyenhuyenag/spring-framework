package com.boot.configuration;

public class MySQL extends Connector {

	@Override
	public void connect() {
		System.out.println("MySQL: " + getUrl());
	}

}
