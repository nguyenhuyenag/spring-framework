package com.service;

import com.model.LIpsum;

public interface DataService {

	void onSuccess(LIpsum ipsum);

	void autoInsert();

	void receiveMessage(String listener, String message);

}
