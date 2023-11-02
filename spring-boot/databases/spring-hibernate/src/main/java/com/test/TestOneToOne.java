package com.test;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.onetoone.withJoinColumn.AddressJC;
import com.entity.onetoone.withJoinColumn.UserJC;
import com.entity.onetoone.withJoinTable.AddressJT;
import com.entity.onetoone.withJoinTable.UserJT;
import com.entity.onetoone.withSharedPrimaryKey.AddressSPK;
import com.entity.onetoone.withSharedPrimaryKey.UserSPK;
import com.repository.AddressJCRepository;
import com.repository.AddressJTRepository;
import com.repository.AddressSPKRepository;
import com.repository.UserJCRepository;
import com.repository.UserJTRepository;
import com.repository.UserSPKRepository;

/**
 * @JoinColumn -> Xóa tự User
 * 
 * @Jointable -> Xóa tự Address
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
	AddressJCRepository addressJCRepository;

	@Autowired
	AddressJTRepository addressJTRepository;

	@Autowired
	AddressSPKRepository addressSPKRepository;

	public void test() {
		// userJCRepository.deleteAll();
		// addressJTRepository.deleteAll();

		// createJoinColumn();
		// createJoinTable();
		// createSharePrimaryKey();

		// readWithJoinTable();
		// readWithJoinColumn();

		// read_UserAddress_SharePrimaryKey();
		read_AddressUser_JC();
	}

	protected void readWithJoinColumn() {
		Optional<UserJC> opt = userJCRepository.findById(4);
		opt.ifPresent(u -> {
			System.out.println(u.getUsername());
			System.out.println(u.getAddress().getStreet());
		});
	}

	protected void read_AddressUser_JC() {
		Optional<AddressJC> opt = addressJCRepository.findById(7);
		opt.ifPresent(t -> {
			System.out.println(t.getStreet());
			System.out.println(t.getUser().getUsername());
		});
	}

	protected void readWithJoinTable() {
		Optional<UserJT> opt = userJTRepository.findById(3);
		opt.ifPresent(u -> {
			System.out.println(u.getUsername());
			System.out.println(u.getAddress().getStreet());
		});
	}

	protected void read_UserAddress_SharePrimaryKey() {
		Optional<UserSPK> opt = userSPKRepository.findById(3);
		opt.ifPresent(u -> {
			System.out.println(u.getUsername());
			System.out.println(u.getAddress().getStreet());
		});
	}

	protected void createJoinTable() {
		AddressJT address = new AddressJT();
		address.setCity("Hà Nội");
		address.setStreet("Võ Nguyên Giáp");

		UserJT user = new UserJT();
		user.setUsername("abc");
		user.setAddress(address);

		userJTRepository.save(user);
	}

	protected void createJoinColumn() {
		AddressJC address = new AddressJC();
		address.setCity("Hồ Chí Minh");
		int n = ThreadLocalRandom.current().nextInt(50, 100);
		address.setStreet(n + " Điệp Biên Phủ");

		UserJC user = new UserJC();
		user.setUsername("huyennv");
		user.setAddress(address);

		userJCRepository.save(user);
	}

	protected void createSharePrimaryKey() {
		AddressSPK address = new AddressSPK();
		address.setCity("Hồ Chí Minh");
		address.setStreet("Lê Đại Hành");

		UserSPK user = new UserSPK();
		int id = ThreadLocalRandom.current().nextInt(50, 99);
		System.out.println("ID: " + id);
		user.setId(id);
		user.setUsername("Nguyễn Văn " + RandomStringUtils.randomAlphabetic(3).toUpperCase());
		user.setAddress(address);

		userSPKRepository.save(user);
	}

}
