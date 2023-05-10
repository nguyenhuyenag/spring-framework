package com.core.synchronize;

public class BankAccount {

	private long amount = 20; // Số tiền có trong tài khoản

	public boolean canCheck(long drawAmount) throws InterruptedException {
		Thread.sleep(1000); // waiting process database
		return (amount >= drawAmount);
	}

	public synchronized void withDraw(String threadName, long withdrawAmount) throws InterruptedException {
		// In thông tin người rút
		System.out.println(threadName + " check: " + withdrawAmount);
		if (canCheck(withdrawAmount)) {
			amount -= withdrawAmount;
			System.out.println(threadName + " withdraw successful: " + withdrawAmount);
		} else {
			System.out.println(threadName + " withdraw error!");
		}
		// In ra số dư tài khoản
		System.out.println(threadName + " see balance: " + amount);
	}

}
