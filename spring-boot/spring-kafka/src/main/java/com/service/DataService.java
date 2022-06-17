package com.service;

import com.entity.Data;

public interface DataService {

	void onSuccess(Data ipsum);

	void autoInsert();

	void receiveMessage(String listener, String message);

}
