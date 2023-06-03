package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primary.repository.CustomerRepository;
import com.second.entity.Office;
import com.second.repository.OfficeRepository;
import com.util.DataUtils;

@Service
public class RepositoryService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OfficeRepository officeRepository;

	public void findAll() {
		find1All();
		find2All();
	}

	private void find1All() {
		List<?> result = customerRepository.findAll();
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

	private void find2All() {
		List<?> result = officeRepository.findAll();
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

	public void save() {
		// save1();
		save2();
	}
	
	public void save1() {
		customerRepository.save(DataUtils.getC1());
	}

	public void save2() {
		officeRepository.save(DataUtils.getOff1());
	}

}
