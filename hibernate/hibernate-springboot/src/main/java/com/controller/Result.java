package com.controller;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Result {

	private int balance;
	private String type;
	private String description;

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(int balance, String type, String description) {
		super();
		this.balance = balance;
		this.type = type;
		this.description = description;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
