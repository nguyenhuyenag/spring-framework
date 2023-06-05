package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

	@Autowired
	UserRepository userRepository;

	@Transactional
	public void rollBackWithStatus() {
		try {
			userRepository.save(DataUtils.passUser()); // Save OK
			userRepository.save(DataUtils.passUser()); // Duplicate entry for key 'email'
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("Rollback! .............");
		}
	}

//	@Transactional(readOnly = false)
//	public void testRollBack() {
//		try {
//			for (int i = 1; i <= 3; i++) {
//				User entity = new User();
//				String name = RandomStringUtils.randomAlphabetic(5).toLowerCase();
//				entity.setName(name);
//				entity.setEmail(name + "@mail.com");
//				repository.save(entity);
//				System.out.println("OK! ...............");
//			}
//		} catch (Exception e) {
//			System.out.println("Exception ....................");
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * rollback transaction cho tất cả các sub-class của Exception ngoại trừ
//	 * EntityNotFoundException
//	 */
//	@Transactional(rollbackFor = Exception.class, noRollbackFor = EntityNotFoundException.class)
//	public void updateAuthorWithRollbackCustom(Long id, String name) {
//
//	}

}
