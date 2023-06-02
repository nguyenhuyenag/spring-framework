package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primary.entity.Customer;
import com.primary.repository.CustomerRepository;
import com.second.entity.Office;
import com.second.repository.OfficeRepository;

@Service
public class TransactionService {

	@Autowired
	CustomerRepository repository1;

	@Autowired
	OfficeRepository repository2;

	public void testTransaction() {
		test1Repository();
		// test2Repository();
	}

	@Transactional("transaction1Manager")
	public void test1Repository() {
		try {
			repository1.delete();
			repository1.save(getC1());
			repository1.save(getC2());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(value = "transaction2Manager")
	public void test2Repository() {
		repository2.save(getOff1());
		repository2.save(getOff2());
	}

	public Customer getC1() {
		Customer c1 = new Customer();
		c1.setCustomerName("Euro+ Shopping Channel");
		c1.setContactLastName("Freyre");
		c1.setContactFirstName("Diego");
		c1.setPhone("(91) 555 94 44");
		c1.setAddressLine1("Moralzarzal");
		c1.setAddressLine2("C86");
		c1.setCity("Madrid");
		c1.setState("28034");
		c1.setPostalCode("44000");
		c1.setCountry("Spain");
		c1.setSalesRepEmployeeNumber(1370);
		c1.setCreditLimit(227600.00);
		return c1;
	}

	public Customer getC2() {
		Customer c2 = new Customer();
		c2.setCustomerName("Euro+ Shopping Channel");
		c2.setContactLastName("Freyre");
		c2.setContactFirstName("Diego");
		c2.setPhone("(91) 555 94 44");
		c2.setAddressLine1("Moralzarzal");
		c2.setAddressLine2("C86");
		c2.setCity("Madrid");
		c2.setState("28034");
		c2.setPostalCode("440001231458214562141211111111111");
		c2.setCountry("Spain");
		c2.setSalesRepEmployeeNumber(1370);
		c2.setCreditLimit(227600.00);
		return c2;
	}

	public Office getOff1() {
		Office office1 = new Office();
		office1.setOfficeCode(8);
		office1.setCity("Tokyo");
		office1.setPhone("+27 10 5887 1952");
		office1.setAddressLine1("89 New Lat Street");
		office1.setAddressLine2("Level 9");
		office1.setCountry("Japan");
		office1.setPostalCode("JC2N 1CR");
		office1.setTerritory("QMEA");
		return office1;
	}

	public Office getOff2() {
		Office office2 = new Office();
		office2.setOfficeCode(9);
		office2.setCity("Tokyo");
		office2.setPhone("+27 10 5887 1952");
		office2.setAddressLine1("89 New Lat Street");
		office2.setAddressLine2("Level 9");
		office2.setCountry("Japan");
		office2.setPostalCode("JC2N 1CR");
		office2.setTerritory("QMEAXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return office2;
	}

}
