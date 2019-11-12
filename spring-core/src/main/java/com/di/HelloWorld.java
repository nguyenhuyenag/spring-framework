package com.di;

public class HelloWorld {

	private String message;

	public HelloWorld() {
		
	}

	public HelloWorld(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void print() {
		System.out.println("Print: " + this.message);
	}

}
