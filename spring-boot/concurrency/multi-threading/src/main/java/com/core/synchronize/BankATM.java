package com.controller.synchronize;

public class BankATM extends Thread {

	private String threadName;
	private long withdrawAmount = 0;
	private BankAccount bankAccount;

	public BankATM(String threadName, BankAccount bankAccount, long withdrawAmount) {
		this.threadName = threadName;
		this.bankAccount = bankAccount;
		this.withdrawAmount = withdrawAmount;
	}

	@Override
	public void run() {
		try {
			bankAccount.withDraw(threadName, withdrawAmount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccount();
		// Chồng rút 15 triệu
		BankATM husband = new BankATM("Husband", bankAccount, 15);
		husband.start();
		// Vợ rút 20 triệu
		BankATM wife = new BankATM("Wife", bankAccount, 20);
		wife.start();
	}

}
