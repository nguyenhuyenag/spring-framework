package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controller.Result;
import com.entity.BankAccount;
import com.repository.BankRepository;
import com.service.BankService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository repository;

	@Override
	public Result deposit() {
		BankAccount account = repository.getOne(1);
		account.setBalance(2);
		repository.save(account);
		return new Result(account.getBalance(), "Nạp tiền", "Giao dịch thành công");
	}

	@Override
	public Result withdraw() {
		Result r = new Result();
		BankAccount account = repository.getOne(1);
		int blance = account.getBalance();
		if (blance > 0) {
			account.setBalance(blance - 1);
			repository.save(account);
			r.setDescription("Giao dịch thành công");
		} else {
			r.setDescription("Số dư không đủ!");
		}
		r.setType("Rút tiền");
		r.setBalance(account.getBalance());
		return r;
	}

}
