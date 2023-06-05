package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;

import com.repository.UserRepository;
import com.util.DataUtils;

/**
 * Define Transaction -> If no custom rollback rules apply, the transaction will
 * roll back on RuntimeException and Error but not on checked exceptions
 * 
 * -> Nếu chúng ta không khai báo rollback rules thì transaction sẽ chỉ rool
 * back khi có exception của RuntimeException và Error. Còn các loại Checked
 * Exceptions sẽ không được rollback
 * 
 * - Spring proxy sẽ tự động rollback transaction nếu có một RuntimeException
 * xảy ra. Ta có thể tùy biến bằng cách sử dụng thuộc tính rollbackFor và
 * noRollbackFor của @Transactional annotation
 */
@Service
public class SpringTransaction {

	// @Autowired
	// private EntityManager entityManager;

	@Autowired
	PlatformTransactionManager transactionManager;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	UserRepository userRepository;

	@Transactional
	public void withStatus() {
		try {
			userRepository.save(DataUtils.passUser()); // Save OK
			userRepository.save(DataUtils.passUser()); // Duplicate entry for key 'email'
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("Rollback! .............");
		}
	}

	public void withTransactionTemplate_1() {
		try {
			transactionTemplate.executeWithoutResult(status -> {
				userRepository.save(DataUtils.passUser());
				userRepository.save(DataUtils.passUser());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void withTransactionTemplate_2() {
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

//	/**
//	 * rollback transaction cho tất cả các sub-class của Exception ngoại trừ
//	 * EntityNotFoundException
//	 */
//	@Transactional(rollbackFor = Exception.class, noRollbackFor = EntityNotFoundException.class)
//	public void updateAuthorWithRollbackCustom(Long id, String name) {
//
//	}

}
