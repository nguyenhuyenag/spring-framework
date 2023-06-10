package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primary.repository.AddressRepository;
import com.second.repository.UserRepository;
import com.util.DataUtils;

@Service
public class RepositoryService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserRepository userRepository;

	public void findAll() {
		find1All();
		find2All();
	}

	private void find1All() {
		List<?> result = addressRepository.findAll();
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

	private void find2All() {
		List<?> result = userRepository.findAll();
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

	public void save() {
		save1();
		save2();
	}
	
	public void save1() {
		addressRepository.save(DataUtils.passAddress());
	}

	public void save2() {
		userRepository.save(DataUtils.passUser());
	}

}
