package dev.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.mysql.cj.xdevapi.Result;

class MyException1 extends Throwable {
	private static final long serialVersionUID = 1L;
}

class MyException2 extends Throwable {
	private static final long serialVersionUID = 1L;
}

/**
 * Spring transactional when using try catch block
 * 
 * - https://stackoverflow.com/a/25739582
 */
@Service
public class Spring2Transaction {

	/**
	 * If there is an exception in the method doStuff the transaction isn't rolled
	 * back. To rollback the exception programmatically, we can do something
	 */
	@Transactional
	public Result doStuff() {
		Result res = null;
		try {
			// do stuff
		} catch (Exception e) {

		}
		return res;
	}

	// Method 1: Declarative approach
	@Transactional(rollbackFor = { MyException1.class, MyException2.class })
	public Result do1Stuff() {
		return null;
	}

	// Method 2: Programmatic rollback
	public Result do2Stuff() {
		try {
			// Do something
		} catch (Exception e) {
			// Trigger rollback programmatically
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return null;
	}

}
