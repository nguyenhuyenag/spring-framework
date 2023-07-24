package com.service;

import com.entity.Data;

public interface DataService {

	void autoInsert();

	void onSuccess(Data ipsum);

	void receiveMessage(String listener, String message);

}
