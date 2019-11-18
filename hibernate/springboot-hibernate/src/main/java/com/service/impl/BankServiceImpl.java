package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.BankAccount;
import com.repository.BankRepository;
import com.service.BankService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class BankServiceImpl implements BankService, Runnable {

	private Thread t;
	private String atmName;

	public BankServiceImpl(String name) {
		this.atmName = name;
		System.out.println("ATM: " + atmName);
	}

	@Autowired
	private BankRepository repository;

	@Override
	public void run() {
		try {
			withdraw();
			Thread.sleep(50);
		} catch (InterruptedException e) {

		}
	}

	public void start() {
		System.out.println("Starting " + atmName);
		if (t == null) {
			t = new Thread(this, atmName);
			t.start();
		}
	}

	@Override
	public void withdraw() {
		BankAccount account = repository.getOne(1);
		int blance = account.getBalance();
		if (blance > 50) {
			account.setBalance(blance - 50);
			System.out.println("Rút 50k ...");
			repository.save(account);
		}
	}

}
