package com.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.entity.TableA;
import com.entity.TableB;
import com.entity.TableC;
import com.entity.Users;
import com.repository.ARepository;
import com.repository.BRepository;
import com.repository.CRepository;
import com.repository.UsersRepository;
import com.service.ITransaction;

@Service
public class ITransactionImpl implements ITransaction {

	@Autowired
	ARepository repoA;

	@Autowired
	BRepository repoB;

	@Autowired
	CRepository repoC;

	@Autowired
	UsersRepository repo;

	@Override
	@Transactional // <- spring
	public void rollBack() {
		try {
			repoA.save(new TableA(null, Integer.parseInt("1")));
			repoB.save(new TableB(null, Integer.parseInt("2")));
			repoC.save(new TableC(null, Integer.parseInt("c")));
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
	}

	@Override
	@Transactional // <- spring
	public void testRollBack() {
		try {
			// repoA.save(new TableA(null, Integer.parseInt("1")));
			// repoB.save(new TableB(null, Integer.parseInt("2")));
			// repoC.save(new TableC(null, Integer.parseInt("c")));
			for (int j = 1; j <= 3; j++) {
				// Creating User Data & Saving It To The Database
				Users userObj = new Users();
				// userObj.setId(j);
				userObj.setName("a" + j);
				userObj.setCreatedBy("Administrator");
				userObj.setCreatedDate(new Date());
				// session.save(userObj);
				repo.save(userObj);
			}
		} catch (Exception e) {
			System.out.println("Exception ....................");
			e.printStackTrace();
		}
	}

}
