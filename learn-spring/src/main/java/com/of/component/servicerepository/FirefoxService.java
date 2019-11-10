package com.of.component.servicerepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

interface IFirefox {

	Firefox getByName(String name);

}

@Repository // connect database
class FirefoxRepository implements IFirefox {

	@Override
	public Firefox getByName(String name) {
		// query ...
		return new Firefox(name);
	}
}

@Service // business logic
public class FirefoxService {

	@Autowired
	private IFirefox iFirefox;

	public Firefox getFirefoxByName() {
		String name = randomName(10);
		return iFirefox.getByName(name);
	}

	public String randomName(int length) {
		return RandomStringUtils.randomAlphanumeric(length).toLowerCase();
	}

}
