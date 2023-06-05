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
 * > Propagation
 * 
 * - REQUIRED: Nếu có một transaction đang hoạt động thì nó sẽ sử dụng chung,
 * nếu không method được gọi sẽ tạo một transaction mới. Đây là giá trị mặc định
 * của Propagation.
 * 
 * - SUPPORTS: Sử dụng lại transaction hiện đang hoạt động. Nếu không thì method
 * được gọi sẽ thực thi mà không được đặt trong một transactional context nào
 * 
 * - MANDATORY: Yêu cầu phải có một transaction đang hoạt động trước khi gọi,
 * nếu không method được gọi sẽ ném ra một exception.
 * 
 * - NEVER: Ném một exception nếu method được gọi trong một transaction hoạt
 * động.
 * 
 * - NOT_SUPPORTED: Dừng transaction hiện tại và thực thi method mà không thuộc
 * một transaction nào
 * 
 * - REQUIRES_NEW: Luôn bắt đầu một transaction mới cho method được gọi. Nếu
 * method được gọi với một transaction đang hoạt động, transaction đó sẽ bị tạm
 * ngưng, một transaction mới sẽ được tạo và sử dụng cho method này. Transaction
 * mới vừa được tạo sẽ thực thi độc lập với transaction bên ngoài, khi
 * transaction này kết thúc dữ liệu sẽ được đồng bộ xuống database. Sau đó
 * transaction bên ngoài sẽ được kích hoạt và hoạt động trở lại.
 * 
 * - NESTED: Method được gọi sẽ tạo một transaction mới nếu không có transaction
 * nào đang hoạt động. Nếu method được gọi với một transaction đang hoạt động
 * Spring sẽ tạo một savepoint và rollback tại đây nếu có Exception xảy ra.
 * 
 * > Read-Only
 * 
 * > Handling Exceptions
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
