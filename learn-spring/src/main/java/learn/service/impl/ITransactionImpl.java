package learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import learn.entity.TableA;
import learn.entity.TableB;
import learn.entity.TableC;
import learn.repository.ARepository;
import learn.repository.BRepository;
import learn.repository.CRepository;
import learn.service.ITransaction;

@Service
public class ITransactionImpl implements ITransaction {

	@Autowired
	ARepository repoA;

	@Autowired
	BRepository repoB;

	@Autowired
	CRepository repoC;

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
	@Transactional(readOnly = true) // <- spring
	public void testRollBack() {
		try {
			repoA.save(new TableA(null, Integer.parseInt("1")));
			repoB.save(new TableB(null, Integer.parseInt("2")));
			repoC.save(new TableC(null, Integer.parseInt("c")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
