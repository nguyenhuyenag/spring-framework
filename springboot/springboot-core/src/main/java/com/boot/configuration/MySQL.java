package com.boot.configuration;

public class MySQL extends Connector {

	@Override
	public void connect() {
		System.out.println("Đã kết nối tới MySQL: " + getUrl());
	}

}
