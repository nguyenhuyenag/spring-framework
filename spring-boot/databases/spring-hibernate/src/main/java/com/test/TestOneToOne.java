package com.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.onetoone.withJoinColumn.AddressJC;
import com.entity.onetoone.withJoinColumn.UserJC;
import com.entity.onetoone.withJoinTable.AddressJT;
import com.entity.onetoone.withJoinTable.UserJT;
import com.entity.onetoone.withSharedPrimaryKey.AddressSPK;
import com.entity.onetoone.withSharedPrimaryKey.UserSPK;
import com.repository.AddressJTRepository;
import com.repository.UserJCRepository;
import com.repository.UserJTRepository;
import com.repository.UserSPKRepository;

/**
 * @JoinColumn -> Xóa tự User
 * 
 * @Jointable  -> Xóa tự Address
 */
@Component
public class TestOneToOne {

	@Autowired
	UserJCRepository userJCRepository;

	@Autowired
	UserJTRepository userJTRepository;
	
	@Autowired
	UserSPKRepository userSPKRepository;

	@Autowired
	AddressJTRepository addressJTRepository;

	public void test() {
		// userJCRepository.deleteAll();
		// addressJTRepository.deleteAll();

		// withJoinColumn();
		// withJoinTable();
		withSharePrimaryKey();
		
		// readWithJoinColumn();
		// readWithJoinTable();
	}

	protected void readWithJoinColumn() {
		Optional<UserJC> opt = userJCRepository.findById(4);
		opt.ifPresent(u -> {
			System.out.println(u.getUsername());
			System.out.println(u.getAddress().getStreet());
		});
	}
	
	protected void readWithJoinTable() {
		Optional<UserJT> opt = userJTRepository.findById(3);
		opt.ifPresent(u -> {
			System.out.println(u.getUsername());
			System.out.println(u.getAddress().getStreet());
		});
	}
	
	protected void withJoinTable() {
		AddressJT address = new AddressJT();
		address.setCity("Hà Nội");
		address.setStreet("Võ Nguyên Giáp");

		UserJT user = new UserJT();
		user.setUsername("abc");
		user.setAddress(address);

		userJTRepository.save(user);
	}

	protected void withJoinColumn() {
		AddressJC address = new AddressJC();
		address.setCity("Ho Chi Minh");
		address.setStreet("Dien Bien Phu");

		UserJC user = new UserJC();
		user.setUsername("huyennv");
		user.setAddress(address);

		userJCRepository.save(user);
	}
	
	protected void withSharePrimaryKey() {
		AddressSPK address = new AddressSPK();
		address.setCity("Hồ Chí Minh");
		address.setStreet("Lê Đại Hành");
		
		UserSPK user = new UserSPK();
		user.setName("Nguyễn Văn A");
		user.setAddress(address);
		
		userSPKRepository.save(user);
	}

}
