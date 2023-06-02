package com.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.primary.entity.Customer;
import com.second.entity.Office;

@Service
public class TransactionService {

	@PersistenceContext
	EntityManager entity1Manager;

	// @Autowired
	// OfficeRepository repository2;

	public void testTransaction() {
		test1Repository();
		// test2Repository();
	}

	// @Scope(proxyMode = ScopedProxyMode.INTERFACES)
	// @Transactional // (propagation = Propagation.REQUIRES_NEW) // , rollbackFor =
	// Exception.class, isolation = Isolation.READ_COMMITTED)
	public void test1Repository() {
		// try {
		// entity1Manager.delete();
		// entity1Manager.persist(getC1());
		// entity1Manager.merge(getC2());
		// } catch (Exception e) {
		// e.printStackTrace();
		// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		// }
	}

//	@Transactional(value = "transaction2Manager")
//	public void test2Repository() {
//		repository2.save(getOff1());
//		repository2.save(getOff2());
//	}

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
