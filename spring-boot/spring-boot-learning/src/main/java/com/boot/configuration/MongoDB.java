package com.boot.configuration;

public class MongoDB extends Connector {
	
	@Override
	public void connect() {
		System.out.println("Đã kết nối tới MongoDB: " + getUrl());
	}
}
