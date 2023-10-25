package com.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.onetoone.withJoinColumn.MyAddress;
import com.entity.onetoone.withJoinColumn.MyUser;
import com.repository.MyUserRepository;

@Component
public class TestOneToOne {

	@Autowired
	MyUserRepository myUserRepository;

	public void test() {
		// myUserRepository.deleteAll();
		// create();
		read();
	}

	protected void read() {
		Optional<MyUser> opt = myUserRepository.findById(4);
		opt.ifPresent(u -> {
			System.out.println(u.getUsername());
			System.out.println(u.getAddress().getStreet());
		});
	}

	protected void create() {
		MyAddress address = new MyAddress();
		address.setCity("Ho Chi Minh");
		address.setStreet("Dien Bien Phu");

		MyUser user = new MyUser();
		user.setUsername("huyennv");
		user.setAddress(address);

		myUserRepository.save(user);
	}

}
