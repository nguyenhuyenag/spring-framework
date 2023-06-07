package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;

import com.repository.AddressRepository;
import com.repository.UserRepository;
import com.util.DataUtils;

/**
 * Define Transaction -> If no custom rollback rules apply, the transaction will
 * roll back on RuntimeException and Error but not on checked exceptions
 * 
 * -> Nếu không khai báo rollback rules thì transaction sẽ chỉ rool back khi có
 * exception của RuntimeException và Error. Còn các loại Checked Exceptions sẽ
 * không được rollback
 * 
 * - Spring proxy sẽ tự động rollback transaction nếu có một RuntimeException
 * xảy ra. Ta có thể tùy biến bằng cách sử dụng thuộc tính rollbackFor và
 * noRollbackFor của @Transactional annotation
 */
@Service
public class SpringTransaction {

	// @Autowired
	// private EntityManager entityManager;

	// @Autowired
	// PlatformTransactionManager transactionManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	private TransactionTemplate transactionTemplate;

	// OK
	@Transactional
	public void withAspect() {
		try {
			userRepository.save(DataUtils.passUser()); // Save OK
			userRepository.save(DataUtils.passUser()); // Duplicate entry for key 'email'
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("Rollback! .............");
		}
	}

	// OK
	public void withTransactionTemplate_1() {
		transactionTemplate.executeWithoutResult(status -> {
			try {
				userRepository.save(DataUtils.passUser());
				userRepository.save(DataUtils.passUser());
			} catch (Exception e) {
				e.printStackTrace();
				status.setRollbackOnly();
			}
		});
	}

	// OK
	public void withTransactionTemplate_2() {
		try {
			transactionTemplate.executeWithoutResult(status -> {
				userRepository.save(DataUtils.passUser());
				userRepository.save(DataUtils.failUser()); // Column 'email' cannot be null
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void save2Table() {
		try {
			userRepository.save(DataUtils.passUser());
			// addressRepository.save(DataUtils.passAddress());
			addressRepository.save(DataUtils.failAddress());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

}
