package dev.boot.configuration;

public class PostgreSQL extends Connector {

	@Override
	public void connect() {
		System.out.println("PostgreSQL: " + getUrl());
	}

}
