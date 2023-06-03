package com.util;

import com.primary.entity.Customer;
import com.second.entity.Office;

public class DataUtils {

	public static Customer getC1() {
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

	public static Office getOff1() {
		Office office1 = new Office();
		office1.setCity("Tokyo");
		office1.setPhone("+27 10 5887 1952");
		office1.setAddressLine1("89 New Lat Street");
		office1.setAddressLine2("Level 9");
		office1.setCountry("Japan");
		office1.setPostalCode("JC2N 1CR");
		office1.setTerritory("QMEA");
		return office1;
	}

}
