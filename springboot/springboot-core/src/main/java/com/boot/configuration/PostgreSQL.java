package com.boot.configuration;

public class PostgreSQL extends Connector {

	@Override
	public void connect() {
		System.out.println("Đã kết nối tới PostgreSQL: " + getUrl());
	}

}