package learn.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import learn.entity.TableA;
import learn.entity.TableB;
import learn.entity.TableC;
import learn.entity.Users;
import learn.repository.ARepository;
import learn.repository.BRepository;
import learn.repository.CRepository;
import learn.repository.UsersRepository;
import learn.service.ITransaction;

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
